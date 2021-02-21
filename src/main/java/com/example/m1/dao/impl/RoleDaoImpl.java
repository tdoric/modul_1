package com.example.m1.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.RoleDao;
import com.example.m1.dao.Statements;
import com.example.m1.model.Role;
import com.example.m1.model.RoleAccount;


@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String USER_ID="userId";

	@Override
	public List<Role> getRolesByUser(Integer userId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_ID,userId);
		return namedParameterJdbcTemplate.query(Statements.Role.GET,params, new BeanPropertyRowMapper<>(Role.class));
	}

	@Override
	public void insertRolesForUser(List<RoleAccount> roleAcc) {
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(roleAcc.toArray());
		namedParameterJdbcTemplate.batchUpdate(Statements.Role.INSERT_ROLE_ACC,parameterSource);
		
	}

}
