
package com.dasmatarix.multiplayer.handler;

/**
 * The Interface IHandler.
 *
 * @param <T> the generic type
 */
public interface IHandler<T> {

	/**
	 * Handle.
	 *
	 * @param object the object
	 * @param clazz  the clazz
	 */
	public void handle(T object);
}
