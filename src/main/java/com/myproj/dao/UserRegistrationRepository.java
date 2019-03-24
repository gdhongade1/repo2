package com.myproj.dao;
import org.springframework.data.repository.CrudRepository;

import com.myproj.pojo.UserRegistration;

public interface UserRegistrationRepository extends CrudRepository<UserRegistration,Long>{

}
