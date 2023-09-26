package com.insurance.Insurance.claims.contractor;

import java.util.List;

import com.insurance.Insurance.claims.models.ClaimBills;
import com.insurance.Insurance.claims.models.Claims;
import com.insurance.Insurance.claims.models.User;

public interface DAOInterface {

	List<Claims> getAllApplicants();

	List<Claims> getAllClaims();

	boolean verifyCredentials(String username, String password);

	List<User> getAllCredentials();

	List<ClaimBills> getRejectedLoans();

	List<ClaimBills> getFilteredClaims(String status);

	List<ClaimBills> getClaimedAmount();

	List<ClaimBills> getTotalAmount();

}
