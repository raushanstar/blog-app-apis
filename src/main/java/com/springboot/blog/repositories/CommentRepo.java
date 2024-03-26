package com.springboot.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment	, Integer>{

}
