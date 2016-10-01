package com.flashvisions.red5.sobridge;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.red5.logging.Red5LoggerFactory;
import org.red5.net.websocket.WebSocketConnection;
import org.red5.server.adapter.MultiThreadedApplicationAdapter;
import org.slf4j.Logger;

import com.flashvisions.red5.sobridge.exceptions.InvalidMessageException;
import com.flashvisions.red5.sobridge.exceptions.MessageWriteException;
import com.flashvisions.red5.sobridge.model.SORecord;
import com.flashvisions.red5.sobridge.messaging.MessageWriter;

public class WebSocketApplicationAdapter {
	
	private MultiThreadedApplicationAdapter handler; 
	
	private Set<WebSocketConnection> connections = new HashSet<WebSocketConnection>();
	private ConcurrentHashMap<String, SORecord> soRecords = new ConcurrentHashMap<String, SORecord>();
	private ConcurrentHashMap<String, ArrayList<WebSocketConnection>> soConnections = new ConcurrentHashMap<String, ArrayList<WebSocketConnection>>();
	
	
	private static Logger logger = Red5LoggerFactory.getLogger(WebSocketApplicationAdapter.class);

	
	public void initialize()
	{
		logger.info("initialize");
	}
	
	
	public void onAppConnect(WebSocketConnection conn)
	{
		logger.info("onAppConnect " + conn.getPath());
		connections.add(conn);
	}
	
	
	public void onAppDisconnect(WebSocketConnection conn)
	{
		logger.info("onAppDisconnect " + conn.getPath());
		
		if(connections.contains(conn))
		connections.remove(conn);
	}
	
	
	// topic => connection/call
	public void invoke(MessageWriter writer, Object[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InvalidMessageException, MessageWriteException
	{
		String methodName = (String) args[0];
		Object[] params = new Object[args.length-1];
		System.arraycopy(args, 1, params, 0, args.length-1);
		
		
		Class<?>[] argumentTypes = createArgumentTypes(params);
		Method method = handler.getClass().getMethod(methodName, argumentTypes );
		Object[] methodArgs = createArguments(params);
		Class<?> returnType = method.getReturnType();
		
		if(returnType == null || returnType.equals(null))
		{
			method.invoke(handler, methodArgs);
		}
		else
		{
			Object response = method.invoke(handler, methodArgs);
			writer.writeWebSocketResponse("/application/invoke", "rmi", response);
		}
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
	
	
	Object[] createArguments(Object[] arguments) {
	    Object[] args = new Object[arguments.length];
	    for (int i = 0; i < arguments.length; i++) {
	    	args[i] = arguments[i];
	    }
	    return args;
	}

	Class<?>[] createArgumentTypes(Object[] arguments) {
	    Class[] types = new Class[arguments.length];
	    for (int i = 0; i < arguments.length; i++) {
	        types[i] = arguments[i].getClass();
	    }
	    return types;
	}


	public ConcurrentHashMap<String, ArrayList<WebSocketConnection>> getSoConnections() {
		return soConnections;
	}


	public void setSoConnections(ConcurrentHashMap<String, ArrayList<WebSocketConnection>> soConnections) {
		this.soConnections = soConnections;
	}


	public ConcurrentHashMap<String, SORecord> getSoRecords() {
		return soRecords;
	}


	public void setSoRecords(ConcurrentHashMap<String, SORecord> soRecords) {
		this.soRecords = soRecords;
	}
}
