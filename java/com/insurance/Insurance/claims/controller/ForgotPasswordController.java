package com.insurance.Insurance.claims.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insurance.Insurance.claims.models.LoginClass;
import com.insurance.Insurance.claims.repositories.AdminRepository;
import com.insurance.Insurance.claims.repositories.EmailRepository;

import jakarta.servlet.http.HttpSession;





@Controller
public class ForgotPasswordController {
	@Autowired
	AdminRepository repadmin;
	
	
	private EmailRepository mailService;
	private final HttpSession httpSession;

	@Autowired
	public ForgotPasswordController(EmailRepository mailService, HttpSession httpSession) {
		this.mailService = mailService;
		this.httpSession = httpSession;
	}
	
	@GetMapping("/email")
	@ResponseBody
	public String email(@RequestParam("to") String to_mail) {
		String email = to_mail;
		httpSession.setAttribute("email", email);
		// storing generated otp
		int OTP = mailService.sendmail(to_mail);
		httpSession.setAttribute("OTP", OTP);
		
		

		return "Email Sent Successfully";

	}
	
	@PostMapping(value = "/validateOTP")
	public ModelAndView validateOTP(@RequestParam("otp") String otp,Model model) {
		model.addAttribute("to", "");
		int OTP=Integer.parseInt(otp);
		ModelAndView mav = new ModelAndView();
		int originalOtp = (Integer) httpSession.getAttribute("OTP");
		String email = (String) httpSession.getAttribute("email");
		// checking the otp sent by the user if true returning reset page else need to stay in the same page with error
		// msg
		if (originalOtp == OTP) {
			mav.setViewName("reset");
			mav.addObject("email", email);
			return mav;
		}
		mav.setViewName("forgotPasswordPage");
		mav.addObject("msg", "Please Enter Valid OTP");
		mav.addObject("email", email);
		return mav;
	}
	
	
	@PostMapping("/reset")
	public String reset(Model model,@RequestParam("email") String email,@RequestParam("pwd") String pwd,@RequestParam("cnfpwd") String cnfpwd) {
		System.out.println(email+" "+pwd+" "+cnfpwd);
		int x=repadmin.resetpwd(email,pwd);
		if(x==1) model.addAttribute("message", "password changed");
		else model.addAttribute("message", "error while password changing");
		model.addAttribute("login", new LoginClass());
		return "adminlogin";
	}
}