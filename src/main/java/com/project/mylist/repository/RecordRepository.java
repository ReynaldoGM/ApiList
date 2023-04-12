package com.project.mylist.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import com.project.mylist.model.Record;

public interface RecordRepository extends ListCrudRepository<Record, Long> {

	//@Query("FROM Record WHERE user.id =?1")
	List<Record> findByUserId(Long userId);
}
