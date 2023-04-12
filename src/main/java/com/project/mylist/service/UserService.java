package com.project.mylist.service;

import java.util.Optional;

import com.project.mylist.exception.ResourceNotFoundException;
import com.project.mylist.model.User;

public interface UserService {
	
	public Optional<User> findById(Long userId);
	public String addNewUser(User user);
	public User updateUser(User userToUpdate,User userRequest);
	public String deleteUser(User user) throws ResourceNotFoundException;
	
	

}
