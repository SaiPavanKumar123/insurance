package com.insurance.Insurance.claims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insurance.Insurance.claims.contractor.IRepository;
import com.insurance.Insurance.claims.models.ClaimBills;
import com.insurance.Insurance.claims.models.Claims;

@Controller

public class InsuranceController {

	IRepository irepo;

	@Autowired
	public InsuranceController(IRepository irepo) {

		this.irepo = irepo;

	}

	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String adminlogin() {

		return "adminlogin";
	}

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		boolean isValid = irepo.verifyCredentials(username, password);

		if (isValid) {
			return "index"; // Redirect to the desired page
		} else {
			return "redirect:/adminlogin"; // Redirect back to the login page
		}
	}

	@RequestMapping(value = "/applicants", method = RequestMethod.GET)
	public String getDashBoard(Model model) {
		List<Claims> claims = irepo.getAllApplicants();
		model.addAttribute("claims", claims);

		List<Claims> claimsunderprocess = irepo.getAllClaims();
		model.addAttribute("claimsunderprocess", claimsunderprocess);

		return "hell";

	}

	@RequestMapping(value = "/rejected", method = RequestMethod.GET)
	public String getAllRejectedLoans(Model model) {
		List<ClaimBills> claimbills = irepo.getRejectedLoans();
		model.addAttribute("rejectedbills", claimbills);

		return "rejected";
	}

	// @GetMapping(value = "/getFilteredClaims")
	// public String getFilteredClaims(Model model, @RequestParam("status") String status) {
	//
	// ArrayList<ClaimBills> claimbills = (ArrayList<ClaimBills>) irepo.getFilteredClaims(status);
	//
	// model.addAttribute("claimbills", claimbills);
	// return "hell";
	// }

	@RequestMapping(value = "/claims", method = RequestMethod.GET)
	public String getClaimedValue(Model model) {
		List<ClaimBills> amtRecived = irepo.getClaimedAmount();
		model.addAttribute("claimedamt", amtRecived);

		List<ClaimBills> totalAmt = irepo.getTotalAmount();
		model.addAttribute("total_amount", totalAmt);

		return "claimvalue";
	}

}
