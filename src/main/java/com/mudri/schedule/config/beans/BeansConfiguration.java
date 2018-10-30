/**
 * SCHEDULE API
 */
package com.mudri.schedule.config.beans;

import org.springframework.context.annotation.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * General application config
 * @author sebamed
 */
@Configuration
public class BeansConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
