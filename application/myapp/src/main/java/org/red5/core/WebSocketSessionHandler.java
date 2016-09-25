package org.red5.core;


import org.red5.logging.Red5LoggerFactory;
import org.red5.net.websocket.WebSocketConnection;
import org.red5.net.websocket.listener.WebSocketDataListener;
import org.red5.net.websocket.model.WSMessage;
import org.slf4j.Logger;

public class WebSocketSessionHandler extends WebSocketDataListener 
{
  	
	private WebSocketApplicationAdapter applicationAdapter;
	
	
	
	private static final Logger log = Red5LoggerFactory.getLogger(WebSocketSessionHandler.class, "chat");
    {
        setProtocol("wsservice");
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
	public void onWSMessage(WSMessage arg0) {
		// TODO Auto-generated method stub

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
