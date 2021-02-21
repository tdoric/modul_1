package com.example.m1.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.GroupArticleDao;
import com.example.m1.dao.Statements;
import com.example.m1.model.GroupArticle;

@Repository
public class GroupArticleDaoImpl implements GroupArticleDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String USER_ID="userId";
	private static final String GROUP_ID="groupsId";

	@Override
	public void insertToGroupArticle(GroupArticle groupArticle) {
		
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(groupArticle);
		namedParameterJdbcTemplate.update(Statements.GroupArticle.INSERT, paramSource);
	}

	@Override
	public boolean checkOwnershipOfGroup(Integer userId, Integer groupId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_ID, userId);
		params.addValue(GROUP_ID, groupId);
		return namedParameterJdbcTemplate.queryForObject(Statements.GroupArticle.CHECK_OWNERSHIP, params,Integer.class)>0;
	}

}
