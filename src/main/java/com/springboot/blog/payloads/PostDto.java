package com.springboot.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	private Integer postId;

	private String title;

	private String content;

	private String imageName;

	private Date date;

	private CategoryDto category;

	private UserDto user;
}