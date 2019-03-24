package com.myproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproj.pojo.UserRegistration;
import com.myproj.service.UserAddressService;
import com.myproj.service.UserRegistrationService;

@RestController
@RequestMapping({"/registration"})
public class UserRegistrationController {
	
	@Autowired
	UserRegistrationService service;
	
	@PostMapping
	public UserRegistration create(@RequestBody UserRegistration reg){
	    return service.save(reg);
	}
	
	@GetMapping
	public List findAll(){
	  return service.findAll();
	}
	@GetMapping(path= {"/{id}"})
	public UserRegistration findById(@PathVariable Long id){
	  return service.findById(id);
	}
	@PutMapping(value="/{id}")
	public UserRegistration updateById(@PathVariable Long id, @RequestBody UserRegistration user) {
		user.setUser_reg_id(id);
		return service.save(user);
	}
	
	@DeleteMapping(path ={"/{id}"})
	public String deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return "success..";
	}
	
}
