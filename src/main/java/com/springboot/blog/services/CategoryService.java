package com.springboot.blog.services;

import java.util.List;

import com.springboot.blog.payloads.CategoryDto;

public interface CategoryService {

	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto category, Integer id);
	CategoryDto getUserById(Integer id);
	List<CategoryDto> getAllCategory();
	
	void deleteCategory(Integer id);
}
