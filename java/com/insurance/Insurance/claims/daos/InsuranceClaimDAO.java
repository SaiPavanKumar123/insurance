package com.insurance.Insurance.claims.daos;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.insurance.Insurance.claims.contractor.DAOInterface;
import com.insurance.Insurance.claims.models.ClaimBills;
import com.insurance.Insurance.claims.models.Claims;
import com.insurance.Insurance.claims.models.User;
import com.insurance.Insurance.claims.rowmappers.ClaimBillsRowMappers;
import com.insurance.Insurance.claims.rowmappers.ClaimsRowMapper;
import com.insurance.Insurance.claims.rowmappers.UserRowMapper;

@Repository
public class InsuranceClaimDAO implements DAOInterface {

	private final JdbcTemplate jdbcTemplate;

	// Define SQL queries as constants
	private static final String SELECT_ALL_CLAIM_BILLS = "SELECT * FROM claims where clam_status='APPR' or clam_status='UPRO' ";
	private static final String SELECT_FULL_CLAIM_BILLS = "SELECT * FROM claims WHERE clam_status = 'APPR'";
	private static final String SELECT_FULL_CLAIMED_AMOUNT = "SELECT * FROM claimbills WHERE clbl_status = 'FULL'";
	private static final String SELECT_TOTAL_AMOUNT = "SELECT * FROM claimbills WHERE clbl_status = 'FULL' or clbl_status='PART' ";
	private static final String SELECT_USER_COUNT = "SELECT COUNT(*) FROM ajayusers WHERE username = ? AND password = ?";
	private static final String SELECT_ALL_USERS = "SELECT username, password FROM ajayusers";
	private static final String SELECT_ALL_REJECTED = "SELECT * FROM claimbills where clbl_status='RJCT' or clbl_status='PART'";
	private static final String SELECT_ALL_Filtered = "select * from claimbills where clbl_status=?";

	public InsuranceClaimDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Claims> getAllApplicants() {
		return jdbcTemplate.query(SELECT_ALL_CLAIM_BILLS, new ClaimsRowMapper());

	}

	@Override
	public List<Claims> getAllClaims() {
		return jdbcTemplate.query(SELECT_FULL_CLAIM_BILLS, new ClaimsRowMapper());
	}

	@Override
	public boolean verifyCredentials(String username, String password) {
		try {
			int count = jdbcTemplate.queryForObject(SELECT_USER_COUNT, Integer.class, username, password);
			// Add logging to check the value of 'count'
			System.out.println("User count: " + count);
			return count > 0;
		} catch (DataAccessException e) {
			// Handle the exception and log it
			e.printStackTrace();
			return false; // Return false in case of an exception
		}
	}

	@Override
	public List<User> getAllCredentials() {
		return jdbcTemplate.query(SELECT_ALL_USERS, new UserRowMapper());
	}

	@Override
	public List<ClaimBills> getRejectedLoans() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(SELECT_ALL_REJECTED, new ClaimBillsRowMappers());

	}

	@Override
	public List getFilteredClaims(String status) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(SELECT_ALL_Filtered, new Object[] { status }, new ClaimBillsRowMappers());
	}

	@Override
	public List<ClaimBills> getClaimedAmount() {

		return jdbcTemplate.query(SELECT_FULL_CLAIMED_AMOUNT, new ClaimBillsRowMappers());
	}

	@Override
	public List<ClaimBills> getTotalAmount() {

		return jdbcTemplate.query(SELECT_TOTAL_AMOUNT, new ClaimBillsRowMappers());
	}

}
