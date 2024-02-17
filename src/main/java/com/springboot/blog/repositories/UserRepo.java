package com.springboot.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entites.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
