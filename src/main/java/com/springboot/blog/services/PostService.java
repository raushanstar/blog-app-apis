package com.springboot.blog.services;

import java.util.List;

import com.springboot.blog.entites.Post;
import com.springboot.blog.payloads.PostDto;

public interface PostService {
	// create post

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePost(Integer postId);

	// get all post
	List<PostDto> getAllpost();

	// get single post
	Post getPostById();

	// get post by category
	List<PostDto> getPostByCategory(Integer categoryId);

	// get post by User
	List<PostDto> getPostByUser(Integer userId);

	// search posts
	List<PostDto> searchPosts(String keyword);

}
