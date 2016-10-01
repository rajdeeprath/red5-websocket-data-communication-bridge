package com.flashvisions.red5.sobridge.utils;

import org.red5.server.api.scope.IScope;

public class Utils {

	public static String getSharedObjectURI(IScope scope, String name)
	{
		return scope.getPath() + scope.getName() + name;
	}
}
