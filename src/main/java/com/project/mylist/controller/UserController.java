package com.project.mylist.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.mylist.exception.ResourceNotFoundException;
import com.project.mylist.model.User;
import com.project.mylist.service.UserService;

import jakarta.validation.Valid;



@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/addNewUser")
	public  String addNewUser( @Valid @RequestBody User user) {
		
		return userService.addNewUser(user);
		
	}
	
	@PutMapping("/updateUser/{userId}")
	public  ResponseEntity<User> updateUser( @PathVariable Long userId,@Valid @RequestBody  User userRequest) throws ResourceNotFoundException {
		User user = userService.findById(userId).map(foundUser ->{
			return userService.updateUser(foundUser, userRequest);
		}).orElseThrow(() ->
				 new ResourceNotFoundException("Not Found User with id = "+userId));
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUSer(@Valid @RequestBody User user) throws ResourceNotFoundException{
		
		return new ResponseEntity<>(userService.deleteUser(user),HttpStatus.OK);
	}
	
	

}
