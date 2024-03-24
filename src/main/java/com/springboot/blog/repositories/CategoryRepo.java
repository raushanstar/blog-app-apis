package com.springboot.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entites.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
