package com.flashvisions.red5.sobridge.model;

public class SORecord {
	
	private String name;
	
	private String path;
	
	private String scopePath;
	
	private Long lastAcquired;
	
	
	public SORecord(String name, String path, String scopePath, Long lastAcquired) {
		this.name = name;
		this.path = path;
		this.scopePath = scopePath;
		this.lastAcquired = lastAcquired;
	} 
	
	public SORecord() {
		
	} 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getScopePath() {
		return scopePath;
	}

	public void setScopePath(String scopePath) {
		this.scopePath = scopePath;
	}

	public Long getLastAcquired() {
		return lastAcquired;
	}

	public void setLastAcquired(Long lastAcquired) {
		this.lastAcquired = lastAcquired;
	}
	
	
}
