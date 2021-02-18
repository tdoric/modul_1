package com.example.m1.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.example.m1.dao.RoleDao;
import com.example.m1.model.Role;
import com.example.m1.model.RoleAccount;


@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Role> getRolesByUser(Integer userId) {
		String sql = "SELECT role_name,status FROM roles";
		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class));
	}

	@Override
	public void insertRolesForUser(List<RoleAccount> roleAcc) {
		String sql = "INSERT INTO ACCOUNT_ROLES(user_id,role_id,status) values (:userId,:roleId,1)";
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(roleAcc.toArray());
		namedParameterJdbcTemplate.batchUpdate(sql,parameterSource);
		
	}

}
