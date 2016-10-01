package com.flashvisions.red5.ws.service.handler;

import java.util.ArrayList;

import org.red5.logging.Red5LoggerFactory;
import org.red5.net.websocket.WebSocketConnection;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.so.ISharedObject;
import org.red5.server.util.ScopeUtils;
import org.slf4j.Logger;

import com.flashvisions.red5.sobridge.WebSocketApplicationAdapter;
import com.flashvisions.red5.sobridge.listeners.SOListener;
import com.flashvisions.red5.sobridge.model.SORecord;
import com.flashvisions.red5.sobridge.utils.Utils;

public class SharedObjectHandler {
	
	WebSocketApplicationAdapter wsAppAdapter;
	
	private static Logger logger = Red5LoggerFactory.getLogger(SharedObjectHandler.class);
	
	public SharedObjectHandler(){
		
	}

	
	// topic => sharedobject/getRemote
	public Object getRemoteSharedObject(WebSocketConnection connection, String name, String path, Boolean persistent)
	{
		ISharedObject so = getSharedObject(name, path, persistent);
		String soURI = Utils.getSharedObjectURI(so.getParent(), name);
		
		if(wsAppAdapter.getSoConnections().containsKey(soURI))
		{
			ArrayList<WebSocketConnection> conns = wsAppAdapter.getSoConnections().get(soURI);
			conns.add(connection);
			
			wsAppAdapter.getSoConnections().put(soURI, conns);
		}
		else
		{
			ArrayList<WebSocketConnection> conns = new ArrayList<WebSocketConnection>();
			wsAppAdapter.getSoConnections().put(soURI, conns);
		}
		
		
		return null;
	}
	
	
	
	// internal
	private ISharedObject getSharedObject(String name, String path, Boolean persistent) 
	{
        IScope appScope = wsAppAdapter.getHandler().getScope();
        IScope application = ScopeUtils.findApplication(appScope);
        IScope scope = ScopeUtils.resolveScope(application, path);
        String soURI = null;
        
        if (scope == null) 
        {
            // attempt to create the missing scope for the given path
            if (!appScope.createChildScope(path)) 
            {
                logger.warn("Scope creation failed for {}", path);
                return null;
            }
            scope = ScopeUtils.resolveScope(appScope, path);
        }
        
        
        ISharedObject so = wsAppAdapter.getHandler().getSharedObject(scope, name);
        if (so == null) 
        {
            if (!wsAppAdapter.getHandler().createSharedObject(scope, name, persistent)) 
            {
                logger.warn("SharedObject creation failed");
                return null;
            }
            // get the newly created shared object
            so = wsAppAdapter.getHandler().getSharedObject(scope, name);
        }
        
        
        soURI = Utils.getSharedObjectURI(so.getParent(), name);
        
        // acquire only once
        if(!wsAppAdapter.getSoRecords().containsKey(soURI)){
        if (!so.isAcquired()) {
        	so.acquire();
            so.addSharedObjectListener(new SOListener(wsAppAdapter, scope, path));
            wsAppAdapter.getSoRecords().put(soURI, new SORecord(name, path, scope.getPath(), System.currentTimeMillis()));
        }
        }
        
        return so;
	}


	public WebSocketApplicationAdapter getWsAppAdapter() {
		return wsAppAdapter;
	}


	public void setWsAppAdapter(WebSocketApplicationAdapter wsAppAdapter) {
		this.wsAppAdapter = wsAppAdapter;
	}

	
	
}
