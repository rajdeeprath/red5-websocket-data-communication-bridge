package com.flashvisions.red5.sobridge.messaging;

import java.io.UnsupportedEncodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.red5.net.websocket.WebSocketConnection;
import org.red5.net.websocket.model.WSMessage;

import com.flashvisions.red5.sobridge.exceptions.InvalidMessageException;
import com.flashvisions.red5.sobridge.exceptions.MessageReadException;
import com.flashvisions.red5.sobridge.model.Message;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class MessageReader {
	
	
	public MessageReader(){
		
	}
	
	
	public Message decodeWebSocketMessage(WSMessage message) throws MessageReadException
	{
		Gson gson = new Gson();
		Message msg = null;
		
		try 
		{
			WebSocketConnection conn = message.getConnection();
			IoBuffer buff = message.getPayload();
			msg = gson.fromJson(message.getMessageAsString(), Message.class);
		} 
		catch (JsonSyntaxException e) 
		{
			throw new MessageReadException(e);
		} 
		catch (UnsupportedEncodingException e) 
		{
			throw new MessageReadException(e);
		}
		
		
		return msg;
	}

}
