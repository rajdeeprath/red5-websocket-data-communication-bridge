package org.red5.core;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import org.red5.net.websocket.WebSocketConnection;
import org.red5.server.adapter.MultiThreadedApplicationAdapter;

public class WebSocketApplicationAdapter {
	
	private MultiThreadedApplicationAdapter handler;
	
	private Set<WebSocketConnection> connections = new HashSet<WebSocketConnection>();
	
	
	
	public void initialize()
	{
		
	}
	
	
	public void onAppConnect(WebSocketConnection conn)
	{
		connections.add(conn);
		
		// TO DO
		
		try {
			conn.send("sending connection update");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void onAppDisconnect(WebSocketConnection conn)
	{
		if(connections.contains(conn))
		connections.remove(conn);
	}
	

	public MultiThreadedApplicationAdapter getHandler() {
		return handler;
	}

	public void setHandler(MultiThreadedApplicationAdapter handler) {
		this.handler = handler;
	}

	public Set<WebSocketConnection> getConnections() {
		return connections;
	}

	public void setConnections(Set<WebSocketConnection> connections) {
		this.connections = connections;
	}
	
	

}
