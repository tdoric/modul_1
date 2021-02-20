package com.example.m1.dao;

import java.util.List;

import com.example.m1.model.Account;

public interface AccountDao {
	
	public Integer insertAccount(Account acc);
	public boolean checkUsername(String username);
	public boolean checkEmail(String mail);
	public boolean checkIfExist(Integer userId);
	public void updateSts(Integer userId,Integer status);
	public void refreshLastLogin(Integer userId);
	public List<Account> getAll();
	public Account findByUsername(String username);
	
}
