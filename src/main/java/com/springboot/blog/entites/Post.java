package com.springboot.blog.entites;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	@Column(name = "post_title", length = 100, nullable = false)
	private String title;

	@Column(length = 10000000)
	private String content;

	private String imageName;

	private Date date;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToOne
	private User user;
}
