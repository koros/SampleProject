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
			Message message1 = createMessageWithTextAndId("What status would you like to report", 1);
			List<Action> actions = getActionsForMessage(message1);
			Message message2 = createMessageWithTextAndId("Enter the name of the person who received the percel", 2);
			List<Action> actions2 = getActionsForMessage(message2);
			Message message3 = createMessageWithTextAndId("Thanks, we have saved all the information on the server, we'll confirm from the client about delivery of the parcel. have a good day", 3);
			List<Action> actions3 = getActionsForMessage(message3);
			Message message4 = createMessageWithTextAndId("We are sorry to hear this, we'll notify the client of the visit", 4);
			List<Action> actions4 = getActionsForMessage(message4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Action> getActionsForMessage(Message message) {
		switch (message.getMessageId()) {
		case 1:
			return getActionsForMessage1();
		case 2:
			return getActionsForMessage2();
		case 3:
			return getActionsForMessage3();
		default:
			break;
		}
		
		return null;
	}
	
	private List<Action> getActionsForMessage1(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(1, "Sucessful delivery", 1, 2);
		Action action2 = createActionWithParams(2, "Customer not found", 1, 3);
		Action action3 = createActionWithParams(3, "Emergency", 1, 4);
		actions.add(action1);
		actions.add(action2);
		actions.add(action3);
		return actions;
	}
	
	private List<Action> getActionsForMessage2(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(4, "", 2, 3, "input");
		actions.add(action1);
		return actions;
	}
	
	private List<Action> getActionsForMessage3(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(5, "", 3, 0, "end");
		actions.add(action1);
		return actions;
	}
	
	private Action createActionWithParams(Integer actionId, String name, int associatedMessageId, int associatedResponseMessageId, String type){
		Action action = new Action();
		action.setActionId(actionId);
		action.setAssociatedMessageId(associatedMessageId);
		action.setAssociatedResponseMessageId(associatedResponseMessageId);
		action.setName(name);
		action.setType(type);
		actionDao.add(action);
		return action;
	}
	
	private Action createActionWithParams(Integer actionId, String name, int associatedMessageId, int associatedResponseMessageId){
		return createActionWithParams(actionId, name, associatedMessageId, associatedResponseMessageId, "text");
	}

	private Message createMessageWithTextAndId(String text, int messageId){
		Message message = new Message();
		message.setSender("server");
		message.setText(text);
		message.setMessageId(messageId);
		messageDao.add(message);
		return message;
	}
	
}
