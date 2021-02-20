package com.example.m1.repository;

import java.util.List;

import com.example.m1.dto.NewsFeed;

public interface NewsFeedRepo {
	
	public List<NewsFeed> getAllNewsFeed();
	public List<NewsFeed> gettNewsFeedByUser(Integer userId);
	

}
