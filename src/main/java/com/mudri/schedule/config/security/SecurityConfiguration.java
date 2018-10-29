/**
 * SCHEDULE API
 */
package com.mudri.schedule.config.security;

/*
  +---------------------------------------------+
  | Name: SecurityConfiguration                                  
  | Author: Sebastian                         
  | Date: Oct 28, 2018                                                                                                                         
  +---------------------------------------------+
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mudri.schedule.utils.TokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	private TokenProvider tokenProvider;

	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
		this.userDetailsService = userDetailsService;
		this.tokenProvider = tokenProvider;
	}

	/**
	 * This method configures authentication manager
	 *
	 * @param authenticationManagerBuilder
	 * @throws Exception
	 */
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		authenticationManagerBuilder.userDetailsService(this.userDetailsService);
	}

	/**
	 * Method witch returns new instance of PasswordEncoder
	 *
	 * @return
	 */
	// in this case, returns 500 with message: "There is no PasswordEncoder mapped
	// for the id \"null\""
	// @Bean
	// public PasswordEncoder passwordEncoder() {
	// return new BCryptPasswordEncoder();
	// }

	/**
	 * This method return instance of AuthenticationManager from super class
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * This method returns instance of AuthenticationTokenFilter
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public JWTFilter authenticationTokenFilterBean() throws Exception {
		JWTFilter authenticationTokenFilter = new JWTFilter(this.tokenProvider, this.userDetailsService);
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/auth/**").permitAll().antMatchers("/api/**").authenticated().and().csrf().disable();

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/app/**/*.{js,html}").antMatchers("/i18n/**")
				.antMatchers("/content/**").antMatchers("/swagger-ui.html").antMatchers("/test/**")
				.antMatchers("/webjars/**");
	}
}
