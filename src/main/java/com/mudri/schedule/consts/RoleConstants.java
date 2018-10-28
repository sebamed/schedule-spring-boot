/**
 * SCHEDULE API
 */
package com.mudri.schedule.consts;

/*
  +---------------------------------------------+
  | Name: RoleConstatns                                  
  | Author: Sebastian                         
  | Date: Oct 28, 2018                                                                                                                         
  +---------------------------------------------+
*/

public class RoleConstants {

	public static final String SUPER_ADMIN = "SUPER_ADMIN";

	public static final String ADMIN = "ADMIN";

	public static final String USER = "USER";

	public static final String ANONYMOUS = "ANONYMOUS";

	private static final String AUTH_ADMIN = "hasAuthority('ADMIN')";

	private static final String AUTH_USER = "hasAuthority('USER')";

	private RoleConstants() {

	}

}
