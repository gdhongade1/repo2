package com.myproj.dao;

import org.springframework.data.repository.CrudRepository;

import com.myproj.pojo.EmailFormatter;

public interface EmailFormatterRepository extends CrudRepository<EmailFormatter,Integer> {

}
