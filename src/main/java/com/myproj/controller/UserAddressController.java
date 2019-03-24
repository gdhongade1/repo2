package com.myproj.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproj.dao.UserAddressRepository;
import com.myproj.pojo.EmailFormatter;
import com.myproj.pojo.UserAddress;
import com.myproj.service.UserAddressService;

@RestController
@RequestMapping({"/address"})
public class UserAddressController {
	
	@Autowired
	UserAddressService service;
	
	@PostMapping
	public UserAddress create(@RequestBody UserAddress addrs){
	    return service.save(addrs);
	}
	
	@GetMapping
	public List findAll(){
	  return service.findAll();
	}
	
	

}
