package com.myproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproj.pojo.EmailFormatter;
import com.myproj.service.EmailFormatterService;
import com.myproj.service.UserAddressService;

@RestController
@RequestMapping({"/email"})
public class EmailFormatterController {
	
	@Autowired
	EmailFormatterService service;
	
	@PostMapping
	public EmailFormatter create(@RequestBody EmailFormatter ef){
	    return service.save(ef);
	}
	
	@GetMapping
	public List findAll(){
	  return service.findAll();
	}

}
