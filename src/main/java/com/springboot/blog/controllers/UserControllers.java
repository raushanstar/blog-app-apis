package com.springboot.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.payloads.UserDto;
import com.springboot.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserControllers {

	@Autowired
	UserService userService;
	// post - create user

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer id) {
		UserDto userdto = userService.updateUser(userDto, id);

		return ResponseEntity.ok(userDto);

	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAlluser());
	}

	// GET - user get
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	
	
}
