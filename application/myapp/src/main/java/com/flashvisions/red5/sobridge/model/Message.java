package com.flashvisions.red5.sobridge.model;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5412396166532662095L;

	String type;
	
	String path;
	
	Long timestamp;
	
	Object content;
	
	
	public Message(){
		
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}


	public Object getContent() {
		return content;
	}


	public void setContent(Object content) {
		this.content = content;
	}
	
	
}
