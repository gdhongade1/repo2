package com.myproj.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproj.service.EmailService;
import com.myproj.service.OTPService;

@RestController
public class OTPController {
	@Autowired
	public OTPService otpService;
	@Autowired
	public EmailService emailService;
	
	@GetMapping("/generateOtp/{userId}")
	public String generateOtp(@PathVariable("userId") Long userId){
		int otp = otpService.generateOTP(userId);
		System.out.println("Generated otp :"+otp +" for user :" +userId);
		String otpStr=String.valueOf(otp);
		
		//Generate The Template to send OTP 
		Map<String,String> replacements = new HashMap<String,String>();
		emailService.sendOtpMessage(otpStr,userId);
		//emailService.sendEmail();
		return "otppage";
	}
	
	@GetMapping(value ="/validateOtp/{userId}/{otpnum}")
	public @ResponseBody String validateOtp(@PathVariable("otpnum") int otpnum,@PathVariable("userId") Long userId){
		final String SUCCESS = "Entered Otp is valid";
		final String FAIL = "Entered Otp is NOT valid. Please Retry!";
		//Validate the Otp 
		if(otpnum >= 0){
			int serverOtp = otpService.getOtp(userId);
				if(serverOtp > 0){
					if(otpnum == serverOtp){
						otpService.clearOTP(userId);
						return ("Entered Otp is valid");
					}else{
						return SUCCESS;
					}
				}else {
					return FAIL;
				}
		}else {
			return FAIL;
		}
	}
}
