package com.example.m1.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.m1.dto.NewsFeed;
import com.example.m1.repository.NewsFeedRepo;
import com.example.m1.repository.Statements;

@Repository
public class NewsFeedRepoImpl implements NewsFeedRepo {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final String USER_ID="userId";

	@Override
	public List<NewsFeed> getAllNewsFeed() {
		return namedParameterJdbcTemplate.query(Statements.NewsFeed.GET_ALL, new BeanPropertyRowMapper<>(NewsFeed.class));

	}

	@Override
	public List<NewsFeed> gettNewsFeedByUser(Integer userId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_ID, userId);
		return namedParameterJdbcTemplate.query(Statements.NewsFeed.GET_SPECIFIC,params, new BeanPropertyRowMapper<>(NewsFeed.class));
	}

}
