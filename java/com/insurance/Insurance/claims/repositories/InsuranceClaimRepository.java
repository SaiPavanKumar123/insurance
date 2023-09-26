package com.insurance.Insurance.claims.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insurance.Insurance.claims.contractor.DAOInterface;
import com.insurance.Insurance.claims.contractor.IRepository;
import com.insurance.Insurance.claims.models.ClaimBills;
import com.insurance.Insurance.claims.models.Claims;
import com.insurance.Insurance.claims.models.User;

@Repository
public class InsuranceClaimRepository implements IRepository {

	@Autowired
	private DAOInterface daoi;

	@Override
	public List<Claims> getAllApplicants() {
		return daoi.getAllApplicants();
	}

	@Override
	public List<Claims> getAllClaims() {
		return daoi.getAllClaims();
	}

	@Override
	public boolean verifyCredentials(String username, String password) {
		return daoi.verifyCredentials(username, password);
	}

	@Override
	public User verifyuser(String username, String password) {
		List<User> users = daoi.getAllCredentials();

		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}

		return null;
	}

	@Override
	public List<ClaimBills> getRejectedLoans() {

		return daoi.getRejectedLoans();
	}

	@Override
	public List<ClaimBills> getFilteredClaims(String status) {

		return (List<ClaimBills>) daoi.getFilteredClaims(status);
	}

	@Override
	public List<ClaimBills> getClaimedAmount() {

		return daoi.getClaimedAmount();
	}

	@Override
	public List<ClaimBills> getTotalAmount() {

		return daoi.getTotalAmount();
	}
}
