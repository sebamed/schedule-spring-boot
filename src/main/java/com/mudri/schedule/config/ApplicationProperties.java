/**
 * SCHEDULE API
 */
package com.mudri.schedule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
  +---------------------------------------------+
  | Name: ApplicationProperties                                  
  | Author: Sebastian                         
  | Date: Oct 28, 2018                                                                                                                         
  +---------------------------------------------+
*/
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
	
    public final Security security = new Security();

	public static class Security {

		private String secret;
		private Long expiration;

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}

		public Long getExpiration() {
			return expiration;
		}

		public void setExpiration(Long expiration) {
			this.expiration = expiration;
		}
	}

	public Security getSecurity() {
		return security;
	}

}
