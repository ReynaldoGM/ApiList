package com.project.mylist.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.mylist.exception.ResourceNotFoundException;
import com.project.mylist.model.User;
import com.project.mylist.model.Record;
import com.project.mylist.repository.RecordRepository;
import com.project.mylist.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RecordRepository recordRepository;
	@Override
	public String addNewUser(User user) {

		userRepository.save(user);

		return "User saved.";
	}

	@Override
	public User updateUser(User userToUpdate, User userRequest) {
		
		userToUpdate.setEmail(userRequest.getEmail());
		userToUpdate.setPassword(userRequest.getPassword());
		userToUpdate.setUserName(userRequest.getUserName());

		userRepository.save(userToUpdate);
		return userToUpdate;
	}

	@Override
	public Optional<User> findById(Long userId) {

		return userRepository.findById(userId);
	}

	@Override
	@Transactional
	public String deleteUser(User user) throws ResourceNotFoundException {
		User userToDelete =userRepository.findById(user.getId()).orElseThrow(
				()-> new ResourceNotFoundException("Not found user with id ="+user.getId()));
		List<Record> records = userToDelete.getRecords();
		records.forEach(record -> recordRepository.delete(record));
		userRepository.delete(user);
		return "User deleted.";
	}

}
