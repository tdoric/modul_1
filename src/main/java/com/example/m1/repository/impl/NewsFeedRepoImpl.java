package com.example.m1.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.m1.dto.NewsFeed;
import com.example.m1.repository.NewsFeedRepo;

@Repository
public class NewsFeedRepoImpl implements NewsFeedRepo {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<NewsFeed> getAllNewsFeed() {
		String sql = "select distinct a.title as articleName,count(l.user_id) as numberLikes from articles a "
				+ 	 " join account_articles aa on a.article_id = aa.article_id and a.status =1 "
				+    " join accounts a2 on aa.user_id =a2.user_id  "
				+ 	 " left join likes l on l.article_id =a.article_id   group by a.title,a2.username ";
		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NewsFeed.class));

	}

	@Override
	public List<NewsFeed> gettNewsFeedByUser(Integer userId) {
		String sql = "select distinct a.title as articleName,count(l.user_id) as numberLikes from articles a "
				+ 	 " join account_articles aa on a.article_id = aa.article_id and a.status =1 "
				+    " join accounts a2 on aa.user_id =a2.user_id  "
				+ 	 " left join likes l on l.article_id =a.article_id where a2.user_id=:userId  group by a.title,a2.username ";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		return namedParameterJdbcTemplate.query(sql,params, new BeanPropertyRowMapper<>(NewsFeed.class));
	}

}
