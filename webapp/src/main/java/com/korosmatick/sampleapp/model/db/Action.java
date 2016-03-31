package com.korosmatick.sampleapp.model.db;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Action implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String type;
	
	private Long associatedMessageId;
	
	private Long associatedResponseMessageId;
	
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

	public Long getAssociatedMessageId() {
		return associatedMessageId;
	}

	public void setAssociatedMessageId(Long associatedMessageId) {
		this.associatedMessageId = associatedMessageId;
	}

	public Long getAssociatedResponseMessageId() {
		return associatedResponseMessageId;
	}

	public void setAssociatedResponseMessageId(Long associatedResponseMessageId) {
		this.associatedResponseMessageId = associatedResponseMessageId;
	}
}
