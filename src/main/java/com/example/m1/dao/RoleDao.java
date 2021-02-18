package com.example.m1.dao;

import java.util.List;

import com.example.m1.model.Role;
import com.example.m1.model.RoleAccount;

public interface RoleDao {
	
	List<Role> getRolesByUser(Integer userId);
	public void insertRolesForUser(List<RoleAccount> roleAcc);

}
