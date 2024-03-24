package com.springboot.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.springboot.blog.payloads.CategoryDto;
import com.springboot.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/category")
public class CategoryControllers {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto category = categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(category, HttpStatus.CREATED);

	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategory(){
		return ResponseEntity.ok(categoryService.getAllCategory()); 
	}
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getSingleCat(@PathVariable Integer catId){
		return ResponseEntity.ok(this.categoryService.getUserById(catId));
	}
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer cid){
		categoryService.deleteCategory(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category delete Successfully", true),HttpStatus.OK);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto catedto, @PathVariable Integer catId){
		CategoryDto catdto = categoryService.updateCategory(catedto, catId);
		return ResponseEntity.ok(catdto);
	}
	
	
}
