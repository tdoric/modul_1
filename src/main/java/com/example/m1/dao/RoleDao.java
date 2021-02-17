package com.example.m1.dao;

import java.util.List;

import com.example.m1.model.Role;

public interface RoleDao {
	
	List<Role> getRolesByUser(Integer userId);

}
