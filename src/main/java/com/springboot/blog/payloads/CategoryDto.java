package com.springboot.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	private int category_id;
	@NotEmpty(message = "title should not be null")
	@Size(min =3,max=20,message="Size must be atleast 3 and max 10")
	private String title;
}

