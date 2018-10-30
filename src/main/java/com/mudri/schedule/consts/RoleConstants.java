/**
 * SCHEDULE API
 */
package com.mudri.schedule.consts;

/**
 * Role constants
 * 
 * @author sansajn
 */
public final class RoleConstants {

	public static final String SUPER_ADMIN = "SUPER_ADMIN";

	public static final String ADMIN = "ADMIN";

	public static final String USER = "USER";

	public static final String ANONYMOUS = "ANONYMOUS";

	public static final String AUTH_ADMIN = "hasAuthority('ADMIN')";

	public static final String AUTH_USER = "hasAuthority('USER')";
	
	public static final String AUTH_USER_ADMIN = "hasAnyAuthority('USER', 'ADMIN')";

}
