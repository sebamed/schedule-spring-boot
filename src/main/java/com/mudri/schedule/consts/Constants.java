/**
 * SCHEDULE API
 */
package com.mudri.schedule.consts;

/*
  +---------------------------------------------+
  | Name: Constants                                  
  | Author: Sebastian                         
  | Date: Oct 23, 2018                                                                                                                         
  +---------------------------------------------+
*/

public final class Constants {
	public static final String USER_ROLE = "USER";
	
	// security
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users/register";
}
