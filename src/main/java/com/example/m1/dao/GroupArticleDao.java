package com.example.m1.dao;

import com.example.m1.model.GroupArticle;

public interface GroupArticleDao {
	
	public void insertToGroupArticle(GroupArticle groupArticle);
	public boolean checkOwnershipOfGroup(Integer userId,Integer groupId);

}
