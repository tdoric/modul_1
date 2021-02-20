package com.example.m1.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Article {
	
	private String title;
	private Date publishDate;
	private Integer status;

}
