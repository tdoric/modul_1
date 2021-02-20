package com.example.m1.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.GroupArticleDao;
import com.example.m1.model.GroupArticle;

@Repository
public class GroupArticleDaoImpl implements GroupArticleDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void insertToGroupArticle(GroupArticle groupArticle) {
		String sql="INSERT INTO GROUPS_ARTICLES(groups_id,article_id,status) values (:groupsId,:articleId,1)";
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(groupArticle);
		namedParameterJdbcTemplate.update(sql, paramSource);
		

	}

	@Override
	public boolean checkOwnershipOfGroup(Integer userId, Integer groupId) {
		String sql="SELECT COUNT(groups_id) from groups where user_id=:userId and groups_id=:groupsId and status=1";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		params.addValue("groupsId", groupId);
		return namedParameterJdbcTemplate.queryForObject(sql, params,Integer.class)>0;
	}

}
