package abc.sampleapp.com.sampleapp.models;

import java.io.Serializable;


public class Action implements Serializable{

	private Integer id;
	
	private String name;
	
	private String type;
	
	private Integer associatedMessageId;
	
	private Integer associatedResponseMessageId;
	
	private Integer actionId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAssociatedMessageId() {
		return associatedMessageId;
	}

	public void setAssociatedMessageId(Integer associatedMessageId) {
		this.associatedMessageId = associatedMessageId;
	}

	public Integer getAssociatedResponseMessageId() {
		return associatedResponseMessageId;
	}

	public void setAssociatedResponseMessageId(Integer associatedResponseMessageId) {
		this.associatedResponseMessageId = associatedResponseMessageId;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
}
