package com.korosmatick.sampleapp.dao;

import java.util.List;

import com.korosmatick.sampleapp.model.db.Message;

public interface MessageDao {
	Message findById(Long id);
	void add(Message message);
	void delete(Message message);
	void deleteById(Long id);
	List<Message> findAll();
}
