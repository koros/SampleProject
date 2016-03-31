package com.korosmatick.sampleapp.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.korosmatick.sampleapp.dao.ActionDao;
import com.korosmatick.sampleapp.dao.MessageDao;
import com.korosmatick.sampleapp.model.db.Action;
import com.korosmatick.sampleapp.model.db.Message;

@Component
public class Bootstrap {
	
	@Autowired
	private ActionDao actionDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@PostConstruct 
	public void initDb(){
		try {
			
			//create dummy messages and actions
			Message message = createMessageWithTextAndId("What status would you like to report", 1L);
			List<Action> actions = getActionsForMessage(message);
			Message message2 = createMessageWithTextAndId("Enter the name of the person who received the percel", 2L);
			List<Action> actions2 = getActionsForMessage(message2);
			Message message3 = createMessageWithTextAndId("Thanks, we have saved all the information on the server, we'll confirm from the client about delivery of the parcel. have a good day", 3L);
			List<Action> actions3 = getActionsForMessage(message3);
			Message message4 = createMessageWithTextAndId("We are sorry to hear this, we'll notify the client of the visit", 4L);
			List<Action> actions4 = getActionsForMessage(message4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Action> getActionsForMessage(Message message) {
		switch (message.getId().intValue()) {
		case 1:
			return getActionsForMessage1();
		case 2:
			return getActionsForMessage2();
		default:
			break;
		}
		
		return null;
	}
	
	private List<Action> getActionsForMessage1(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(1, "Sucessful delivery", 1L, 2L);
		Action action2 = createActionWithParams(2, "Customer not found", 1L, 3L);
		Action action3 = createActionWithParams(3, "Emergency", 1L, 4L);
		actions.add(action1);
		actions.add(action2);
		actions.add(action3);
		return actions;
	}
	
	private List<Action> getActionsForMessage2(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(4, "", 1L, 2L, "input");
		actions.add(action1);
		return actions;
	}
	
	private List<Action> getActionsForMessage3(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(4, "", 1L, 2L, "end");
		actions.add(action1);
		return actions;
	}
	
	private Action createActionWithParams(Integer id, String name, Long associatedMessageId, Long associatedResponseMessageId, String type){
		Action action = new Action();
		action.setId(id);
		action.setAssociatedMessageId(associatedMessageId);
		action.setAssociatedResponseMessageId(associatedResponseMessageId);
		action.setName(name);
		action.setType(type);
		actionDao.add(action);
		return action;
	}
	
	private Action createActionWithParams(Integer id, String name, Long associatedMessageId, Long associatedResponseMessageId){
		return createActionWithParams(id, name, associatedMessageId, associatedResponseMessageId, "text");
	}

	private Message createMessageWithTextAndId(String text, Long id){
		Message message = new Message();
		message.setSender("server");
		message.setText(text);
		message.setId(id);
		messageDao.add(message);
		return message;
	}
	
}
