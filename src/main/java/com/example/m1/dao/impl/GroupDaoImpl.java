package com.example.m1.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.GroupDao;
import com.example.m1.dao.Statements;
import com.example.m1.model.Group;

@Repository
public class GroupDaoImpl implements GroupDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void createGroup(Group group) {
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(group);
		namedParameterJdbcTemplate.update(Statements.Group.INSERT, paramSource);
	}

}
