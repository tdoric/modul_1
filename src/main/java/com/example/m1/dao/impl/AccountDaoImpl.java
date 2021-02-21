package com.example.m1.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.AccountDao;
import com.example.m1.dao.Statements;
import com.example.m1.model.Account;

@Repository
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String PK_ACCOUNT="user_id";
	private static final String USER_ID="userId";
	private static final String USERNAME="username";
	private static final String EMAIL="mail";
	private static final String STATUS="status";
	
	@Override
	public List<Account> getAll() {
		return namedParameterJdbcTemplate.query(Statements.Account.GET_ACC, new BeanPropertyRowMapper<>(Account.class));
	}

	@Override
	public Integer insertAccount(Account acc) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(acc);
	    namedParameterJdbcTemplate.update(Statements.Account.INSERT, paramSource, keyHolder, new String[]{PK_ACCOUNT});
		return keyHolder.getKey().intValue();
	}

	@Override
	public boolean checkUsername(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USERNAME, username);
		return namedParameterJdbcTemplate.queryForObject(Statements.Account.CHECK_USERNAME, params,Integer.class)>0;
	}

	@Override
	public boolean checkEmail(String mail) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(EMAIL, mail);
		return namedParameterJdbcTemplate.queryForObject(Statements.Account.CHECK_EMAIL, params,Integer.class)>0;
	}

	@Override
	public void updateSts(Integer userId,Integer status) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(STATUS,status);
		params.addValue(USER_ID,userId);
		namedParameterJdbcTemplate.update(Statements.Account.UPDATE_STS,params);
		
	}

	@Override
	public void refreshLastLogin(Integer userId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_ID,userId);
		namedParameterJdbcTemplate.update(Statements.Account.REFRESH_LOGIN,params);
		
	}

	@Override
	public Account findByUsername(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USERNAME,username);
		return namedParameterJdbcTemplate.queryForObject(Statements.Account.FIND_ACCOUNT,params,new BeanPropertyRowMapper<>(Account.class));
	}

	@Override
	public boolean checkIfExist(Integer userId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_ID, userId);
		return namedParameterJdbcTemplate.queryForObject(Statements.Account.CHECK_EXIST, params,Integer.class)>0;
	}

}
