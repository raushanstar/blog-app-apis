package com.springboot.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.blog.security.CustomUserDetailsService;
import com.springboot.blog.security.JwtAuthenticationEntryPoint;
import com.springboot.blog.security.JwtAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

	@Autowired
    private CustomUserDetailsService customUserDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("filterchain.. "+ http.toString());    
		return http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		    		.httpBasic()
		        .and().csrf()
	            .disable()
		        .authorizeRequests()
		        .requestMatchers("/api/v1/auth/login").permitAll()
		        .anyRequest().authenticated().and().exceptionHandling()
		        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
		        .and().
		        sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        .and().build();
	 }
	
	@Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
