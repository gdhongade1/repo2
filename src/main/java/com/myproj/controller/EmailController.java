package com.myproj.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproj.dao.EmailFormatterRepository;
import com.myproj.dao.UserRegistrationRepository;
import com.myproj.pojo.Email;
import com.myproj.pojo.EmailFormatter;
import com.myproj.pojo.UserRegistration;
import com.myproj.service.TaskSchedularService;
@RestController
public class EmailController {
	@Autowired
	UserRegistrationRepository dao;
	@Autowired
	EmailFormatterRepository dao1;
	
	@RequestMapping("/check")  
    public String checkEmail(){  
		TaskSchedularService es = new TaskSchedularService();
		  String host = "pop.gmail.com";
	      String mailStoreType = "pop3";
	      String username = "gdhongade08523@gmail.com";
	      String password = "achievers8523";
	     // es.check(host, mailStoreType, username, password);
	     // Email e =es.checkEmail(host, mailStoreType, username, password);
	      es.downloadEmailAttachments(host, "995", username, password);
	      
        return "Success...";  
    }  
	@RequestMapping("/check2")  
    public String checkEmail2(){  
		 EmailFormatter ef= new EmailFormatter();
		 ef.setActualDate("sfsf");
         ef.setActualTime("dfhdf");
         ef.setAttachment("url");
         ef.setBodyContent("hfdhfd");
         ef.setBccEmail("hfd");
         ef.setCcEmail("hdfhgd");
         ef.setEmail_id(1);
         ef.setFavourite(true);
         ef.setFromEmail("hdfhd");
         ef.setFromCustomer("dfhd");
         ef.setLabels("label1");
         ef.setSubject("dhdfh");
         ef.setSubjectCustomer("dfhdf");
         ef.setTaskCompleted(true);
         ef.setToEmail("dfhdf");
         ef.setUser_id(1L);
         ef.setVisible(true);
         
	      dao1.save(ef);
        return "Success...";  
    }  
	/*@RequestMapping("/add")  
    public String addEmail(){  
		UserRegistration user = new UserRegistration();
		user.setConfirmPassword("pwd");
		user.setEmailAddress("MyEmailAdd");
		user.setFirstName("Ganesh");
		user.setLastName("Dhongade");
		user.setMobileNumber("456562626262");
		user.setUser_id(1);
		dao.save(user);
		
        return "Success...";  
    }  */
	@RequestMapping("/em")  
    public @ResponseBody Iterable<UserRegistration> getEmail(){ 
		
		return dao.findAll();
		
	}
	@RequestMapping("/em1")  
    public @ResponseBody Iterable<EmailFormatter> getEmailFormat(){ 
		
		return dao1.findAll();
		
	}
	

}
