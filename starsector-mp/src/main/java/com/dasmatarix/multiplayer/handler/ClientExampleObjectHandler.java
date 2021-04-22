
package com.dasmatarix.multiplayer.handler;

import com.dasmatarix.multiplayer.model.ExampleObject;

/**
 * The Class ExampleObjectHandler.
 */
public class ClientExampleObjectHandler implements IHandler<ExampleObject> {

	/**
	 * Handle.
	 *
	 * @param object the object
	 * @param clazz  the clazz
	 */
	@Override
	public void handle(ExampleObject object) {
		System.out.println("I am the client and this is an example object: " + object.toString());
	}

}
