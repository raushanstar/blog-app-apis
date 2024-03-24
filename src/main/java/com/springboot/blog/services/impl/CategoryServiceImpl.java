package com.springboot.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.entites.Category;
import com.springboot.blog.exceptions.ResourcesNotFoundException;
import com.springboot.blog.payloads.CategoryDto;
import com.springboot.blog.repositories.CategoryRepo;
import com.springboot.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryReop;
//	@Autowired
//    private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
//		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category cat = dtoToCategory(categoryDto);
		Category category = categoryReop.save(cat);
//		return this.modelMapper.map(category, CategoryDto.class);
		return categoryToCategoryDto(category);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category = categoryReop.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "Id", id));
		category.setTitle(categoryDto.getTitle());
		Category savedCat = categoryReop.save(category);
		CategoryDto categoryToCategoryDto = categoryToCategoryDto(savedCat);
		return categoryToCategoryDto;
	}

	@Override
	public CategoryDto getUserById(Integer id) {
		Category category = categoryReop.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "Id", id));
		return categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryReop.findAll();
		List<CategoryDto> collect = categories.stream().map(cat -> categoryToCategoryDto(cat))
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category cat = categoryReop.findById(id).orElseThrow(() -> new ResourcesNotFoundException("User", "Id", id));
		categoryReop.delete(cat);
	}

	public Category dtoToCategory(CategoryDto categoryDto) {

		Category cat = new Category();
		cat.setCategory_id(categoryDto.getCategory_id());
		cat.setTitle(categoryDto.getTitle());
		return cat;

	}

	public CategoryDto categoryToCategoryDto(Category cat) {

		CategoryDto catDto = new CategoryDto();
		catDto.setCategory_id(cat.getCategory_id());
		catDto.setTitle(cat.getTitle());
		return catDto;

	}

}
