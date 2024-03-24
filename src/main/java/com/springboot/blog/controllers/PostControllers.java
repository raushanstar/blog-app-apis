package com.springboot.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.payloads.PostDto;
import com.springboot.blog.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostControllers {
	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	@GetMapping("/user/category/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		return ResponseEntity.ok(postService.getAllpost());
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> post = postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
	}
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> post = postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
	}
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully deleted !!", true);
	}
}
