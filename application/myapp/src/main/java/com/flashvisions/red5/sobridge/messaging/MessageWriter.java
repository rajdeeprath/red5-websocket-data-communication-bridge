package com.flashvisions.red5.sobridge.messaging;

import java.io.UnsupportedEncodingException;

import org.red5.net.websocket.WebSocketConnection;
import org.red5.net.websocket.model.WSMessage;

import com.flashvisions.red5.sobridge.exceptions.InvalidMessageException;
import com.flashvisions.red5.sobridge.exceptions.MessageWriteException;
import com.flashvisions.red5.sobridge.model.Message;
import com.google.gson.Gson;

public class MessageWriter {
	
	
	WebSocketConnection connection;
	
	
	public MessageWriter()
	{
		
	}
	
	
	
	public MessageWriter(WebSocketConnection connection)
	{
		this.connection = connection;
	}
	
	
	public void writeWebSocketMessage(WebSocketConnection connection, Message message) throws InvalidMessageException, MessageWriteException
	{
		Gson gson = new Gson();
		String json = null;
		
		try 
		{
			json = gson.toJson(message);
			connection.send(json);
		} 
		catch (UnsupportedEncodingException e) 
		{
			throw new MessageWriteException(e);
		}
	}
	
	
	
	public void writeWebSocketMessage(Message message) throws InvalidMessageException, MessageWriteException
	{
		Gson gson = new Gson();
		String json = null;
		
		try 
		{
			json = gson.toJson(message);
			connection.send(json);
		} 
		catch (UnsupportedEncodingException e) 
		{
			throw new MessageWriteException(e);
		}
	}
	
	
	
	public void writeWebSocketResponse(WebSocketConnection connection, String path, String type, Object content) throws InvalidMessageException, MessageWriteException
	{
		Gson gson = new Gson();
		String json = null;
		
		try 
		{
			if(path == null || type == null)
			throw new InvalidMessageException("Invalid message parameter(s)");
			
			Message message = new Message();
			message.setPath(path);
			message.setType(type);
			message.setTimestamp(System.currentTimeMillis());
			message.setContent(content);
			
			json = gson.toJson(message);
			connection.send(json);
		} 
		catch (UnsupportedEncodingException e) 
		{
			throw new MessageWriteException(e);
		}
	}
	
	
	
	public void writeWebSocketResponse(String path, String type, Object content) throws InvalidMessageException, MessageWriteException
	{
		Gson gson = new Gson();
		String json = null;
		
		try 
		{
			if(path == null || type == null)
			throw new InvalidMessageException("Invalid message parameter(s)");
			
			Message message = new Message();
			message.setPath(path);
			message.setType(type);
			message.setTimestamp(System.currentTimeMillis());
			message.setContent(content);
			
			json = gson.toJson(message);
			connection.send(json);
		} 
		catch (UnsupportedEncodingException e) 
		{
			throw new MessageWriteException(e);
		}
	}



	public WebSocketConnection getConnection() {
		return connection;
	}



	public void setConnection(WebSocketConnection connection) {
		this.connection = connection;
	}
	
	
	
	

}
