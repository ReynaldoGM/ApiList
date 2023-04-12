package com.project.mylist.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Record {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String name;
	private String description;
	private List<String> items = new ArrayList <String>();
	private List<String> tags = new ArrayList <String>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;
	
	protected Record() {}
	
	public Record(String name,List<String> items, List <String> tags) {
		this.name = name;
		this.items = items;
		this.tags = tags;
		
	}
	
	  @Override
	  public String toString() {
	    return String.format(
	        "Record[id=%d, name='%s', items='%s']",
	        id, name, items);
	  }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getItems() {
		return items;
	}
	public void setItems(List<String> items) {
		this.items = items;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
