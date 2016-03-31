package com.korosmatick.sampleapp.model.api;

import java.util.List;

import com.korosmatick.sampleapp.model.db.Action;
import com.korosmatick.sampleapp.model.db.Message;

public class ActionableMessage {

	private Message message;
	
	private List<Action> posibleActions;
	
	private boolean responseExpected;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Action> getPosibleActions() {
		return posibleActions;
	}

	public void setPosibleActions(List<Action> posibleActions) {
		this.posibleActions = posibleActions;
	}

	public boolean isResponseExpected() {
		return responseExpected;
	}

	public void setResponseExpected(boolean responseExpected) {
		this.responseExpected = responseExpected;
	}
}
