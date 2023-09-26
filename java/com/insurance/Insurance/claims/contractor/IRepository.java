package com.insurance.Insurance.claims.contractor;

import java.util.List;

import com.insurance.Insurance.claims.models.ClaimBills;
import com.insurance.Insurance.claims.models.Claims;
import com.insurance.Insurance.claims.models.User;

public interface IRepository {

	// List<ClaimBills> getAllApplicants();
	//
	List<Claims> getAllClaims();

	boolean verifyCredentials(String user, String password);

	User verifyuser(String username, String password);

	List<ClaimBills> getRejectedLoans();

	List<ClaimBills> getFilteredClaims(String status);

	List<Claims> getAllApplicants();

	List<ClaimBills> getClaimedAmount();

	List<ClaimBills> getTotalAmount();

}
