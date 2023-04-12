package com.project.mylist.service;
import com.project.mylist.model.Record;
import com.project.mylist.repository.RecordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	private RecordRepository recordRepository;

	@Override
	public Optional<Record> findbyId(Long recordId) {
		
		return recordRepository.findById(recordId);
	}

	@Override
	public List<Record> getRecordList() {
		
		List<Record> r = new ArrayList<>();
		recordRepository.findAll().forEach(r::add);
		
		return r;
	}

	@Override
	public String addNewRecord(Record newRecord) {
		recordRepository.save(newRecord);
		return "Record saved";
	}

	@Override
	public List<Record> getRecordList(Long userId) {
		// TODO Auto-generated method stub
		return recordRepository.findByUserId(userId);
	}

	@Override
	public String deleteRecord(Record record) {
		
		 recordRepository.delete(record);
		 return "Record deleted." ;
	}

	@Override
	public Record updateRecord(Record recordToUpdate, Record recordRequest) {
		
		
		recordToUpdate.setName(recordRequest.getName());
		recordToUpdate.setDescription(recordRequest.getDescription());
		recordToUpdate.setItems(recordRequest.getItems());
		recordToUpdate.setTags(recordRequest.getTags());
		return recordRepository.save(recordToUpdate);
		
	}
}
