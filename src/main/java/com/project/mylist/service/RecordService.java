package com.project.mylist.service;

import java.util.List;
import java.util.Optional;

import com.project.mylist.model.Record;

public interface RecordService{
	
	public Optional<Record> findbyId(Long recordId);
	public String addNewRecord(Record newRecord);
	public List<Record> getRecordList();
	public List<Record> getRecordList(Long userId);
	public String deleteRecord(Record record);
	public Record updateRecord(Record recordToUpdate,Record recordRequest);


}