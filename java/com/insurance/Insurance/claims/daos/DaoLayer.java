package com.insurance.Insurance.claims.daos;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.insurance.Insurance.claims.models.LoginClass;


@Component
public class DaoLayer {
	@Autowired
	JdbcTemplate jdbc ;
	
	public int checkCredentials(LoginClass lc) {
		
        String sql = "SELECT COUNT(*) FROM checktable WHERE username = '"+lc.getUser_name()+"' and password='"+lc.getPassword()+"'";
        System.out.println(sql);
        // Execute the SQL query and return the count
        return jdbc.queryForObject(sql, Integer.class);
    }

	public int resetpwd(String email, String pwd) {
		return jdbc.update("update checktable set password='"+pwd+"' where username='"+email+"'");
	}
	
	//
}