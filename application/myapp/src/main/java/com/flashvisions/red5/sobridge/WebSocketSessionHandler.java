package com.flashvisions.red5.sobridge;


import java.io.UnsupportedEncodingException;

import org.red5.logging.Red5LoggerFactory;
import org.red5.net.websocket.WebSocketConnection;
import org.red5.net.websocket.listener.WebSocketDataListener;
import org.red5.net.websocket.model.MessageType;
import org.red5.net.websocket.model.WSMessage;
import org.slf4j.Logger;

import com.flashvisions.red5.sobridge.exceptions.InvalidMessageException;
import com.flashvisions.red5.sobridge.messaging.MessageReader;
import com.flashvisions.red5.sobridge.model.Message;

public class WebSocketSessionHandler extends WebSocketDataListener 
{
  	
	private WebSocketApplicationAdapter applicationAdapter;
	
	private static Logger logger = Red5LoggerFactory.getLogger(WebSocketSessionHandler.class);

	private MessageReader decoder;
	
	private static final Logger log = Red5LoggerFactory.getLogger(WebSocketSessionHandler.class, "chat");
    {
        setProtocol("myapp");
    }
    

	@Override
	public void onWSConnect(WebSocketConnection arg0) {
		// TODO Auto-generated method stub
		applicationAdapter.onAppConnect(arg0);
	}

	
	@Override
	public void onWSDisconnect(WebSocketConnection arg0) {
		// TODO Auto-generated method stub
		applicationAdapter.onAppDisconnect(arg0);
	}

	
	@Override
	public void onWSMessage(WSMessage message) {
		// TODO Auto-generated method stub
		Message msg;
		MessageType type = message.getMessageType();
		
		logger.debug("onWSMessage");
		logger.debug("message type = " + type);
		
		switch(type)
		{
			case TEXT:
			break;			
			
			case PONG:
			break;
			
			case PING:
			break;
			
			case CONTINUATION:
			break;
			
			case BINARY:
			break;	
			
			case CLOSE:
			break;	
			
			default:
			break;
		}

	}
	
	

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return super.getProtocol();
	}

	
	
	@Override
	public void setProtocol(String protocol) {
		// TODO Auto-generated method stub
		super.setProtocol(protocol);
	}

	
	
	public WebSocketApplicationAdapter getApplicationAdapter() {
		return applicationAdapter;
	}

	
	
	public void setApplicationAdapter(WebSocketApplicationAdapter applicationAdapter) {
		this.applicationAdapter = applicationAdapter;
	}

}
