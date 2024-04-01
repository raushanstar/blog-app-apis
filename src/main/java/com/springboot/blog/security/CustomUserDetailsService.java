package com.springboot.blog.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.blog.entites.User;
import com.springboot.blog.repositories.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserName... "+username);
		var user = userRepo.findByEmail(username);
		System.out.println("database search by username "+user.toString());
		return user.map(SecurityUser::new)
		        .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
	}

}
