package com.springboot.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.entites.Category;
import com.springboot.blog.entites.Post;
import com.springboot.blog.entites.User;
import com.springboot.blog.exceptions.ResourcesNotFoundException;
import com.springboot.blog.payloads.CategoryDto;
import com.springboot.blog.payloads.PostDto;
import com.springboot.blog.repositories.CategoryRepo;
import com.springboot.blog.repositories.PostRepo;
import com.springboot.blog.repositories.UserRepo;
import com.springboot.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourcesNotFoundException("user", "userId", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "category id ", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourcesNotFoundException("Post ", "post id", postId));
		postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllpost() {
		List<Post> posts = postRepo.findAll();
		List<PostDto> collect = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public Post getPostById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "Id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> collect = posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourcesNotFoundException("User", "Id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> collect = posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
