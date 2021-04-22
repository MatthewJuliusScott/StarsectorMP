
package com.dasmatarix.multiplayer;

import java.util.HashMap;
import java.util.Map;

import com.dasmatarix.multiplayer.exception.HandlerNotFoundException;
import com.dasmatarix.multiplayer.handler.IHandler;

/**
 * The Class MessageHandler. Handles any message that is received by Class type.
 */
public class MessageHandler {

	/**
	 * The class set. All classes that will be sent as messages have to be
	 * registered here to be automatically handled.
	 */
	@SuppressWarnings("rawtypes")
	protected Map<Integer, IHandler> register = new HashMap<Integer, IHandler>();

	/**
	 * Register.
	 *
	 * @param handler  the handler
	 * @param hashCode the hash code of the Object.getName().hashCode() for the
	 *                 type the handler handles
	 */
	@SuppressWarnings("rawtypes")
	public void register(IHandler handler, int hashCode) {
		register.put(hashCode, handler);
	}

	/**
	 * Handle.
	 *
	 * @param object the object
	 * @param hashCode  the clazz
	 * @throws HandlerNotFoundException the handler not found exception
	 */
	// TODO work out if its find to always handle with a void return and no
	// context. Off the top of my head it works for Starsector because
	// everything can be statically accessed, via Global.getSector() but I'm
	// also trying to keep this code application agnostic. I could conceivably
	// pass more to the handler so it has context, but I want to avoid having
	// the handler return the object, because that will end up with a long chain
	// of code to handle a message inside read().
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void handle(Object object, int hashCode)
	        throws HandlerNotFoundException {

		IHandler handler = register.get(hashCode);
		if (handler == null)
			throw new HandlerNotFoundException("Handler not found. Register a handler for it on the server and the client before trying to handle an object of that type.");
		handler.handle(object);
	}
}
