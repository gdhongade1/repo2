package com.myproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.myproj.dao.EmailFormatterRepository;
import com.myproj.pojo.EmailFormatter;

@Service
public class EmailFormatterService {
	@Autowired
	EmailFormatterRepository dao;
	
	public EmailFormatter save(EmailFormatter ef){
	    return dao.save(ef);
	}
	
	public List findAll(){
		  return (List) dao.findAll();
	}

}
