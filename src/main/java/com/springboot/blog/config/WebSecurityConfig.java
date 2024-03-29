package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		var usm = new InMemoryUserDetailsManager();
		UserDetails u1 = User.withUsername("admin")
		.password("1234")
		.build();
		
		usm.createUser(u1);
		
		return usm;
		
	}
	
	 @Bean
	  public PasswordEncoder passwordEncoder() {
	    return NoOpPasswordEncoder.getInstance();
	  }

}
