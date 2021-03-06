package com.example.m1.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
	
	public Account(String username, String email, String encodePassword) {
		this.username=username;
		this.email=email;
		this.password=encodePassword;
	}
	private Integer userId;
	private String username;
	private String password;
	private String email;
	List<Role> roles;
	
}
