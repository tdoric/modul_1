package com.example.m1.repository;

public final class Statements {
	
	private static final String UTILITY="Utility class";
	
	 private Statements() {
		 throw new IllegalStateException(UTILITY);
     }

	
	public static class NewsFeed{
		private NewsFeed() {
			 throw new IllegalStateException(UTILITY);
	     }
		public static final String GET_ALL = "select distinct a.title as articleName,count(l.user_id) as numberLikes from articles a "
				+ 	 " join account_articles aa on a.article_id = aa.article_id and a.status =1 "
				+    " join accounts a2 on aa.user_id =a2.user_id  "
				+ 	 " left join likes l on l.article_id =a.article_id   group by a.title,a2.username ";
		public static final String GET_SPECIFIC = "select distinct a.title as articleName,count(l.user_id) as numberLikes from articles a "
				+ 	 " join account_articles aa on a.article_id = aa.article_id and a.status =1 "
				+    " join accounts a2 on aa.user_id =a2.user_id  "
				+ 	 " left join likes l on l.article_id =a.article_id where a2.user_id=:userId  group by a.title,a2.username ";
	}
	
}
