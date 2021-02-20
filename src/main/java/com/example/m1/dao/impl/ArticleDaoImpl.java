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
import com.example.m1.model.Article;
import com.example.m1.model.ArticleAccount;

@Repository
public class ArticleDaoImpl implements ArticleDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Integer insertArticle(Article article) {
		String sql="INSERT INTO ARTICLES (title,publish_date,status) values (:title,current_date,1)";
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(article);
		KeyHolder keyHolder = new GeneratedKeyHolder();
	    namedParameterJdbcTemplate.update(sql, paramSource, keyHolder, new String[]{"article_id"});
	    return keyHolder.getKey().intValue();
	}

	@Override
	public void insertAuthorsOfArticle(List<ArticleAccount> acc) {
		String sql="INSERT INTO ACCOUNT_ARTICLES(user_id,article_id,status) values(:userId,:articleId,1)";
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(acc.toArray());
		namedParameterJdbcTemplate.batchUpdate(sql,parameterSource);

	}

}
