package com.springboot.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entites.Category;
import com.springboot.blog.entites.Post;
import com.springboot.blog.entites.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

}
