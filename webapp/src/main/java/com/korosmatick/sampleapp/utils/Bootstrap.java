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
			Message message2 = createMessageWithTextAndId("Thanks, we have saved all the information on the server, we'll confirm from the client about delivery of the parcel. have a good day", 2);
			List<Action> actions2 = getActionsForMessage(message2);
			Message message3 = createMessageWithTextAndId("We are sorry to hear this, we'll notify the client of the visit, <br/> Would you like to wait a few more minutes for the client", 3);
			List<Action> actions3 = getActionsForMessage(message3);
			Message message4 = createMessageWithTextAndId("Thanks we appreciate your effort", 4); // After Yes response
			List<Action> actions4 = getActionsForMessage(message4);
			Message message5 = createMessageWithTextAndId("No wories we'll notify the client of your visit", 5); // After No response
			List<Action> actions5 = getActionsForMessage(message5);
			
			//Emergency response
			Message message6 = createMessageWithTextAndId("We are very sorry to hear this what kind of emergency", 6); // After No response
			List<Action> actions6 = getActionsForMessage(message6);
			Message message7 = createMessageWithTextAndId("Sorry to hear this we'll relay the status update to the client", 7); // After Heavy traffic
			List<Action> actions7 = getActionsForMessage(message7);
			Message message8 = createMessageWithTextAndId("We're very sorry to hear this, we'll send help immediately", 8); // 
			List<Action> actions8 = getActionsForMessage(message8);
			
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
		case 4:
			return getActionsForMessage4();
		case 5:
			return getActionsForMessage5();
		case 6:
			return getActionsForMessage6();
		case 7:
			return getActionsForMessage7();
		case 8:
			return getActionsForMessage8();
		default:
			break;
		}
		
		return null;
	}
	
	private List<Action> getActionsForMessage1(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(1, "Sucessful delivery", 1, 2);
		Action action2 = createActionWithParams(2, "Customer not found", 1, 3);
		Action action3 = createActionWithParams(3, "Emergency", 1, 6);
		actions.add(action1);
		actions.add(action2);
		actions.add(action3);
		return actions;
	}
	
	private List<Action> getActionsForMessage2(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(5, "", 2, 0, "end");
		actions.add(action1);
		return actions;
	}
	
	private List<Action> getActionsForMessage3(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(6, "Yes", 3, 4);
		Action action2 = createActionWithParams(7, "No", 3, 5);
		actions.add(action1);
		actions.add(action2);
		return actions;
	}
	
	private List<Action> getActionsForMessage4(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(8, "", 4, 0, "end");
		actions.add(action1);
		return actions;
	}
	
	private List<Action> getActionsForMessage5(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(9, "", 5, 0, "end");
		actions.add(action1);
		return actions;
	}
	
	private List<Action> getActionsForMessage6(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(10, "Unexpected Traffic", 6, 7);
		Action action2 = createActionWithParams(11, "Acident", 6, 8);
		actions.add(action1);
		actions.add(action2);
		return actions;
	}
	
	private List<Action> getActionsForMessage7(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(12, "", 7, 0, "end");
		actions.add(action1);
		return actions;
	}
	
	private List<Action> getActionsForMessage8(){
		List<Action> actions = new ArrayList<Action>();
		Action action1 = createActionWithParams(13, "", 8, 0, "end");
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
