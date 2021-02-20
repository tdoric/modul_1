package com.example.m1.dao;

import java.util.List;

import com.example.m1.model.Article;
import com.example.m1.model.ArticleAccount;

public interface ArticleDao {
	
	public Integer insertArticle(Article article);
	public void insertAuthorsOfArticle(List<ArticleAccount> acc);

}
