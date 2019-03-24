package com.myproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.myproj.dao.UserAddressRepository;
import com.myproj.dao.UserRegistrationRepository;
import com.myproj.pojo.UserRegistration;

@Service
public class UserRegistrationService {
	@Autowired
	UserRegistrationRepository dao;
	
	public UserRegistration save( UserRegistration reg){
	    return dao.save(reg);
	}
	
	public List findAll(){
		  return (List) dao.findAll();
		}
	public UserRegistration findById(Long id){
		  return  dao.findOne(id);
		}
	
	public String deleteById( Long id) {
		dao.delete(id);
		return "success..";
	}

}
