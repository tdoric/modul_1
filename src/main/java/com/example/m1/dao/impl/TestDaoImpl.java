package com.example.m1.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.AccountDao;
import com.example.m1.model.Account;

@Repository
public class TestDaoImpl implements AccountDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Account> getAll() {
		String sql = "SELECT username FROM accounts";
		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class));
	}

	@Override
	public void insertAccount(Account acc) {
		String sql ="insert  into accounts(username,password,email,created_on,status) "
				+ "values(:username,:password,:email,current_timestamp,1);";
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(acc);
		namedParameterJdbcTemplate.update(sql, paramSource);
		
	}

	@Override
	public boolean checkUsername(String username) {
		String sql="select count(user_id) from accounts where username=:username";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		return namedParameterJdbcTemplate.queryForObject(sql, params,Integer.class)>0;
	}

	@Override
	public boolean checkEmail(String mail) {
		String sql="select count(user_id) from accounts where email=:mail";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("mail", mail);
		return namedParameterJdbcTemplate.queryForObject(sql, params,Integer.class)>0;
	}

	@Override
	public void updateSts(Integer userId,Integer status) {
		String sql="update accounts set status=:status where user_id=:userId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("status",status);
		params.addValue("userId",userId);
		namedParameterJdbcTemplate.update(sql,params);
		
	}

	@Override
	public void refreshLastLogin(Integer userId) {
		String sql="update accounts set last_login=current_timestamp where user_id=:userId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId",userId);
		namedParameterJdbcTemplate.update(sql,params);
		
	}

	@Override
	public Account findByUsername(String username) {
		String sql = "SELECT user_id,username,password,email FROM accounts where username=:username";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username",username);
		return namedParameterJdbcTemplate.queryForObject(sql,params,new BeanPropertyRowMapper<>(Account.class));
	}

}
