package com.springboot.blog.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

import com.springboot.blog.entites.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityRoles implements GrantedAuthority {

	private final Role role;

	@Override
	public String getAuthority() {
		return role.getName();
	}

}
