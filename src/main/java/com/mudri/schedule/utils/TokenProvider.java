/**
 * SCHEDULE API
 */
package com.mudri.schedule.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
  +---------------------------------------------+
  | Name: TokenProvider                                  
  | Author: Sebastian                         
  | Date: Oct 28, 2018                                                                                                                         
  +---------------------------------------------+
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mudri.schedule.config.ApplicationProperties;
import com.mudri.schedule.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Token provider
 * 
 * @author sansajn
 */
@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger("errors");

    private final ApplicationProperties applicationProperties;

    private final String secret;

    private final Long expiration;

    @Autowired
    public TokenProvider(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.secret = applicationProperties.getSecurity().getSecret();
        this.expiration = applicationProperties.getSecurity().getExpiration();
    }

    /**
     * This method returns user name from token
     * @param token
     * @return String user name
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = this.getClaimsFromToken(token.split(" ")[1]);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * This method returns object Claims from token
     * @param token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.secret)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            claims = null;
        }
        return claims;
    }

    /**
     * This method returns date of expiration token
     * @param token
     * @return Date Expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        Date expirationDate;
        try {
            final Claims claims = this.getClaimsFromToken(token.split(" ")[1]);
            expirationDate = claims.getExpiration();
        } catch (Exception e) {
            expirationDate = null;
        }
        return expirationDate;
    }

    /**
     * This method checks is token expired
     * @param token
     * @return boolean true if token is not expired, else false
     */
    private boolean isTokenExpired(String token) {
        final Date expirationDate = this.getExpirationDateFromToken(token);
        return expirationDate.before(new Date(System.currentTimeMillis()));
    }


    /**
     * This method checks if token is valid
     * @param token
     * @param userDetails
     * @return boolean true if token is valid, else false
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    /**
     * This method generates token and returns string that represents token for user
     * @param user
     * @return token
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getEmail());
        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 100000))
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }
}
