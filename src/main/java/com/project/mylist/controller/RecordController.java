package com.project.mylist.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.mylist.exception.ResourceNotFoundException;
import com.project.mylist.model.Record;
import com.project.mylist.service.RecordService;
import com.project.mylist.service.UserService;

@RestController
public class RecordController {

	@Autowired
	UserService userService;

	@Autowired
	RecordService recordService;

	@PostMapping("/addRecord/{userId}")
	public  ResponseEntity<String> addNewRecord( @PathVariable(value = "userId") Long userId,
			@RequestBody Record newRecord) throws ResourceNotFoundException  {

		
			userService.findById(userId).map(user -> {
				newRecord.setUser(user);
				return recordService.addNewRecord(newRecord);

			}).orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + userId));

			return new ResponseEntity<>("Record saved.", HttpStatus.CREATED);
		
	}

	@GetMapping("/records")
	public List<Record> getRecordList() {

		return recordService.getRecordList();

	}
	
	@GetMapping("/records/{userId}/{recordId}")
	public Optional<Record> getRecord(@PathVariable Long recordId,@PathVariable Long userId) throws ResourceNotFoundException {

		return recordService.findbyId(recordId);

	}

	@GetMapping("/records/{userId}")
	public List<Record> getRecordList(@PathVariable Long userId) throws ResourceNotFoundException {
		
		 userService.findById(userId).orElseThrow(
				 ()->new ResourceNotFoundException("Not found user with id = " + userId));
			
		 return recordService.getRecordList(userId);
	}
	
	@DeleteMapping("/records")
	public  ResponseEntity<String> deleteRecord(@RequestBody Record record){
		
		
		return new ResponseEntity<>(recordService.deleteRecord(record),HttpStatus.OK);
	}
	
	@PutMapping("/records/{recordId}")
	public @ResponseBody ResponseEntity<Record> updateRecord(@PathVariable Long recordId, @RequestBody Record recordRequest) throws ResourceNotFoundException{
		Record updatedRecord=recordService.findbyId(recordId).map( record -> {
		 return	recordService.updateRecord(record,recordRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("Not Found Record with id = "+recordId));
		
		return new ResponseEntity<>(updatedRecord,HttpStatus.OK);
		
	}
	
}
