package com.example.m1.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.ArticleDao;
import com.example.m1.dao.Statements;
import com.example.m1.model.Article;
import com.example.m1.model.ArticleAccount;

@Repository
public class ArticleDaoImpl implements ArticleDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String PK_ARTICLE="article_id";

	@Override
	public Integer insertArticle(Article article) {
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(article);
		KeyHolder keyHolder = new GeneratedKeyHolder();
	    namedParameterJdbcTemplate.update(Statements.Article.INSERT, paramSource, keyHolder, new String[]{PK_ARTICLE});
	    return keyHolder.getKey().intValue();
	}

	@Override
	public void insertAuthorsOfArticle(List<ArticleAccount> acc) {
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(acc.toArray());
		namedParameterJdbcTemplate.batchUpdate(Statements.Article.INSERT_ACC,parameterSource);

	}

}
