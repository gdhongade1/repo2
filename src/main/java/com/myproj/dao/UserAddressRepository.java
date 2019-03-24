package com.myproj.dao;

import org.springframework.data.repository.CrudRepository;

import com.myproj.pojo.UserAddress;

public interface UserAddressRepository extends CrudRepository<UserAddress, Integer>{

}
