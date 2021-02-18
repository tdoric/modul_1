package com.example.m1.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.LikeDao;
import com.example.m1.model.Like;

@Repository
public class LikeDaoImpl implements LikeDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void insertLike(Like like) {
		String sql="INSERT INTO LIKES(user_id,article_id,like_date) "
				+ "values(:userId,:articleId,current_timestamp)";
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(like);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}

}
