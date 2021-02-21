package com.example.m1.dao;

public final class Statements {
	
	private static final String UTILITY="Utility class";
	
	 private Statements() {
		 throw new IllegalStateException(UTILITY);
     }

	
	public static class Account{
		private Account() {
			 throw new IllegalStateException(UTILITY);
	     }
		public static final String GET_ACC = "SELECT username FROM accounts";
		public static final String INSERT ="insert  into accounts(username,password,email,created_on,status) "
				+ "values(:username,:password,:email,current_timestamp,1);";
		public static final String CHECK_USERNAME="select count(user_id) from accounts where username=:username";
		public static final String CHECK_EMAIL="select count(user_id) from accounts where email=:mail";
		public static final String UPDATE_STS="update accounts set status=:status where user_id=:userId";
		public static final String REFRESH_LOGIN="update accounts set last_login=current_timestamp where user_id=:userId";
		public static final String FIND_ACCOUNT = "SELECT user_id,username,password,email FROM accounts where username=:username";
		public static final String CHECK_EXIST="select count(user_id) from accounts where user_id=:userId and status=1";
	}
	public static class Article{
		private Article() {
			 throw new IllegalStateException(UTILITY);
	     }
		public static final String INSERT="INSERT INTO ARTICLES (title,publish_date,status) values (:title,current_date,1)";
		public static final String INSERT_ACC="INSERT INTO ACCOUNT_ARTICLES(user_id,article_id,status) values(:userId,:articleId,1)";
	}
	
	public static class GroupArticle{
		private GroupArticle() {
			 throw new IllegalStateException(UTILITY);
	    }
		public static final String INSERT="INSERT INTO GROUPS_ARTICLES(groups_id,article_id,status) values (:groupsId,:articleId,1)";
		public static final String CHECK_OWNERSHIP="SELECT COUNT(groups_id) from groups where user_id=:userId and groups_id=:groupsId and status=1";
	}
	
	public static class Group{
		private Group() {
			 throw new IllegalStateException(UTILITY);
	    }
		public static final String INSERT="INSERT INTO GROUPS(user_id,groupname,status) values (:userId,:groupname,1 )";
	}
	
	public static class Like{
		private Like() {
			 throw new IllegalStateException(UTILITY);
	    }
		public static final String INSERT="INSERT INTO LIKES(user_id,article_id,like_date) values(:userId,:articleId,current_timestamp)";
	}
	
	public static class Role{
		private Role() {
			 throw new IllegalStateException(UTILITY);
	    }
		public static final String GET = "SELECT role_name,status FROM roles";
		public static final String INSERT_ROLE_ACC = "INSERT INTO ACCOUNT_ROLES(user_id,role_id,status) values (:userId,:roleId,1)";
		
	}
	

}
