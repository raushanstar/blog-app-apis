package com.springboot.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.entites.Category;
import com.springboot.blog.entites.Post;
import com.springboot.blog.entites.User;
import com.springboot.blog.exceptions.ResourcesNotFoundException;
import com.springboot.blog.payloads.PostDto;
import com.springboot.blog.payloads.PostResponse;
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
		Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourcesNotFoundException("Post ", "post id", postId));

        Category category = this.categoryRepo.findById(postDto.getCategory().getCategory_id()).get();

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setCategory(category);


        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		postRepo.deleteById(postId); 
	}

	@Override
	public PostResponse getAllpost(Integer pageSize, Integer pageNumber,String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		PageRequest p = PageRequest.of(pageNumber, pageSize,sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        
		List<PostDto> collect = allPosts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return new PostResponse(collect, pagePost.getNumber(), pagePost.getSize(), pagePost.getTotalElements(), pagePost.getTotalPages(), pagePost.isLast());
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post posts = postRepo.findById(postId).orElseThrow(()-> new ResourcesNotFoundException("post", "Id", postId));
		return modelMapper.map(posts, PostDto.class);
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
		List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
	}

}
