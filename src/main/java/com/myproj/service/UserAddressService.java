package com.myproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.myproj.dao.UserAddressRepository;
import com.myproj.pojo.UserAddress;

@Service
public class UserAddressService {
	@Autowired
	UserAddressRepository dao;
	
	public UserAddress save(UserAddress addrs){
	    return dao.save(addrs);
	}

	public List findAll(){
		  return (List) dao.findAll();
	}
}
