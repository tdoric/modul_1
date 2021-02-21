package com.example.m1.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.LikeDao;
import com.example.m1.dao.Statements;
import com.example.m1.model.Like;

@Repository
public class LikeDaoImpl implements LikeDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void insertLike(Like like) {
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(like);
		namedParameterJdbcTemplate.update(Statements.Like.INSERT, paramSource);
	}

}
