package com.flashvisions.red5.sobridge.listeners;

import java.util.List;
import java.util.Map;

import org.red5.server.adapter.MultiThreadedApplicationAdapter;
import org.red5.server.api.IAttributeStore;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.so.ISharedObjectBase;
import org.red5.server.api.so.ISharedObjectListener;

import com.flashvisions.red5.sobridge.WebSocketApplicationAdapter;

public class SOListener implements ISharedObjectListener {
	
	WebSocketApplicationAdapter adapter; 
	MultiThreadedApplicationAdapter handler; 
	String path;
	IScope scope;
	
	public SOListener(WebSocketApplicationAdapter adapter, IScope scope, String path)
	{
		this.adapter = adapter;
		this.scope = scope;
		this.path = path;
	}

	@Override
	public void onSharedObjectClear(ISharedObjectBase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSharedObjectConnect(ISharedObjectBase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSharedObjectDelete(ISharedObjectBase arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSharedObjectDisconnect(ISharedObjectBase arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSharedObjectSend(ISharedObjectBase arg0, String arg1,
			List<?> arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSharedObjectUpdate(ISharedObjectBase arg0,
			IAttributeStore arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSharedObjectUpdate(ISharedObjectBase arg0,
			Map<String, Object> arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSharedObjectUpdate(ISharedObjectBase arg0, String arg1, Object arg2) {
		// TODO Auto-generated method stub
		
		

	}

}
