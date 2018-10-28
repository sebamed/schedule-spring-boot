/**
 * SCHEDULE API
 */
package com.mudri.schedule.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.model.AppUser;
import com.mudri.schedule.repository.UserRepository;


/**
 * Authenticate a user from the database.
 * @author sansajn
 */
@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email) {
        Optional<AppUser> userByUsernameFromDatabase = userRepository.findOneByEmail(email);
        return userByUsernameFromDatabase.map(user -> createSpringSecurityUser(user)).orElseThrow(() ->
                new NotFoundException("User " + email + " was not found in the " +
                        "database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(AppUser user)  {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthorities);
    }
}
