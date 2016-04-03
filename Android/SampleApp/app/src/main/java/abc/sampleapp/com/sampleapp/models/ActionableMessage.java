package abc.sampleapp.com.sampleapp.models;

import java.util.List;


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
