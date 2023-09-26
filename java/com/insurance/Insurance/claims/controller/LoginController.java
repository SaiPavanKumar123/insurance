package com.insurance.Insurance.claims.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.insurance.Insurance.claims.models.LoginClass;
import com.insurance.Insurance.claims.models.OTPclass;
import com.insurance.Insurance.claims.repositories.AdminRepository;

import jakarta.servlet.http.HttpServletRequest;




@Controller
public class LoginController {
	
	AdminRepository rep;
	@Autowired
	public LoginController(AdminRepository rep){
		this.rep = rep;
		
	}
	
	@GetMapping("/login")
	
	public String login(Model model) {
		model.addAttribute("login", new LoginClass());
		return "adminlogin";
	}
	
	@PostMapping("/adminLogin")
	public String adminlogin(HttpServletRequest request, @ModelAttribute("login") LoginClass lc, Model model ) {
		System.out.println("came to this method  "+lc);
		int check=rep.checkCredentials(lc);
		if(check==1) {
			return "index";
		}
		
		model.addAttribute("user_name", "lc.getUser_name()");
        model.addAttribute("password", "lc.getPassword()");
        model.addAttribute("errorMessage", "incorrect credentials"); 
		return "adminlogin";
	}
	@GetMapping("/forgotpassword")
	public String forgotpassword(Model model) {
		model.addAttribute("to", "");
		model.addAttribute("login", new OTPclass());
		model.addAttribute("enotp", "");
		model.addAttribute("otp", "");
		
		return "forgotpassword";
	}
	
}