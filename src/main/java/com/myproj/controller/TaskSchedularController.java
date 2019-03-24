package com.myproj.controller;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myproj.GlobalProperties;
import com.myproj.dao.EmailFormatterRepository;
import com.myproj.pojo.EmailFormatter;
import com.myproj.service.EmailService;
import com.myproj.service.TaskSchedularService;

@Component
public class TaskSchedularController {
	@Autowired
	EmailFormatterRepository dao;
	@Autowired
	 Environment env;
	@Autowired
	GlobalProperties prop;
	 
	//@Scheduled(cron ="${emailFetch.trigger}") 
	 public void checkEmail(){  
				EmailService es = new EmailService();
				String keyValue = env.getProperty("spring.datasource.url");
			  String host = prop.getEmailHost();
		      String mailStoreType = prop.getEmailStoreType();
		      String username = prop.getEmailUsername();
		      String password = prop.getEmailPassword();
		      String port = prop.getEmailPort();
		     // es.check(host, mailStoreType, username, password);
		     // Email e =es.checkEmail(host, mailStoreType, username, password);
		      ArrayList<EmailFormatter> ef=es.downloadEmailAttachments(host, port, username, password);
		      for(EmailFormatter e: ef) {
		    	 dao.save(e);
		      }
		     
		      System.out.println(" runTask2 Current time is :: " + Calendar.getInstance().getTime());
	  }  
}
