package com.insurance.Insurance.claims.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insurance.Insurance.claims.daos.DaoLayer;
import com.insurance.Insurance.claims.models.LoginClass;



@Repository
public class AdminRepository {
	@Autowired
	DaoLayer dao;
	public int checkCredentials(LoginClass lc) {
		return dao.checkCredentials(lc);
	}
	public int resetpwd(String email, String pwd) {
		return dao.resetpwd(email,pwd);
	}
	
}