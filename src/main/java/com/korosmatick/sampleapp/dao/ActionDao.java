package com.korosmatick.sampleapp.dao;

import java.util.List;

import com.korosmatick.sampleapp.model.db.Action;


public interface ActionDao {
	Action findById(Long id);
	Action findActionByName(String name);
	void add(Action action);
	void delete(Action action);
	void deleteById(Integer id);
	List<Action> findAll();
	List<Action> findAllActionsForMessageId(Long messageId);
}
