package com.project.mylist.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.mylist.model.User;

public interface UserRepository extends CrudRepository <User,Long>{

}
