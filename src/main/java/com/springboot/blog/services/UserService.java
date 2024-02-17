package com.springboot.blog.services;

import java.util.List;

import com.springboot.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAlluser();
	
	void deleteUser(Integer userId);

}
