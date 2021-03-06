
package com.dasmatarix.multiplayer;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dasmatarix.multiplayer.exception.HandlerNotFoundException;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.dasmatarix.multiplayer.handler.IHandler;
import com.generated.src._AutoGeneratedSerializerManager;

/**
 * The Interface MessageManager. A central location for adding message
 * serializers and handlers. Model classes should be used, but also new classes
 * can be made with serializers and handlers to represent a specific message.
 * E.g HelloWorldHandler and HelloWorldSerializer might be a message from
 * client/server to server/client represented by the HelloWorld class that just
 * tells the recipeint to print "Hello World".
 */
public class MessageManager {

	/**
	 * The message serializer. This needs to be static as you should serialize a
	 * class the same for any instance of MessageManager.
	 */
	private static MessageSerializer	serializer		= MessageSerializer.getInstance();

	/** The message handler. */
	public MessageHandler				messageHandler	= new MessageHandler();

	/**
	 * Instantiates a new message manager.
	 */
	@SuppressWarnings("rawtypes")
	public MessageManager() {
		Map<Class, ISerializer> classSerializerMap = _AutoGeneratedSerializerManager.getSerializers();
		for (Entry<Class, ISerializer> entry : classSerializerMap.entrySet()) {
			MessageManager.serializer.register(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Register message.
	 *
	 * @param serializer the serializer
	 * @param handler    the handler
	 * @param clazz      the clazz
	 */
	@SuppressWarnings("rawtypes")
	public void registerMessage(ISerializer serializer, IHandler handler,
	        Class clazz) {
		MessageManager.serializer.register(clazz, serializer);
		registerHandler(handler, clazz);
	}

	/**
	 * Register handler.
	 *
	 * @param handler the handler
	 * @param clazz   the clazz
	 */
	@SuppressWarnings("rawtypes")
	private void registerHandler(IHandler handler, Class clazz) {
		messageHandler.register(handler, clazz.getName().hashCode());
	}

	/**
	 * Gets the serializer instance.
	 *
	 * @return the serializer instance
	 */
	public MessageSerializer getSerializerInstance() {
		return serializer;
	}

	/**
	 * Handle.
	 *
	 * @param bytes    the bytes
	 * @param hashCode the hash code
	 * @throws HandlerNotFoundException    the handler not found exception
	 * @throws SerializerNotFoundException the serializer not found exception
	 * @throws IOException                 Signals that an I/O exception has
	 *                                     occurred.
	 */
	public void handle(byte[] bytes, int hashCode)
	        throws HandlerNotFoundException, SerializerNotFoundException,
	        IOException {
		Object object = serializer.deserialize(bytes, hashCode);
		messageHandler.handle(object, hashCode);
	}

	/**
	 * Serialize.
	 *
	 * @param object the object
	 * @param clazz  the clazz
	 * @return the byte[]
	 * @throws SerializerNotFoundException the serializer not found exception
	 * @throws IOException                 Signals that an I/O exception has
	 *                                     occurred.
	 */
	@SuppressWarnings("rawtypes")
	public byte[] serialize(Object object, Class clazz)
	        throws SerializerNotFoundException, IOException {
		return serializer.serialize(object, clazz.getName().hashCode());
	}

	/**
	 * Gets the header.
	 *
	 * @param body  the body
	 * @param clazz the clazz
	 * @return the header
	 */
	@SuppressWarnings("rawtypes")
	public byte[] getHeader(byte[] body, Class clazz) {
		return serializer.getHeader(body, clazz);
	}
}
