package com.springboot.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.blog.entites.Role;
import com.springboot.blog.entites.User;
import com.springboot.blog.exceptions.ResourcesNotFoundException;
import com.springboot.blog.payloads.UserDto;
import com.springboot.blog.repositories.RoleRepo;
import com.springboot.blog.repositories.UserRepo;
import com.springboot.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);

		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourcesNotFoundException("User", "Id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = userRepo.save(user);
		UserDto userToUserDto = userToUserDto(updatedUser);

		return userToUserDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourcesNotFoundException("User", "Id", userId));
		

		return userToUserDto(user);
	}

	@Override
	public List<UserDto> getAlluser() {
		List<User> users = userRepo.findAll();
		List<UserDto> collect = users.stream().map(user -> userToUserDto(user)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourcesNotFoundException("User", "Id", userId));
		
		userRepo.delete(user);

	}
	

	public User dtoToUser(UserDto userDto) {

		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());

		return user;

	}

	public UserDto userToUserDto(User user) {

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());

		return userDto;

	}
	

}
