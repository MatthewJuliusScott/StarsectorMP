
package com.dasmatarix.multiplayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.dasmatarix.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageSerializer.
 *
 */
public class MessageSerializer {

	/**
	 * The Constant HEADER_LENGTH. How many bytes long the header is. The header
	 * should include the message length so we always know exactly how many bytes to
	 * read to make one single message.
	 */
	public static final int HEADER_LENGTH = 2 * (Integer.SIZE / 8);

	/**
	 * The class set. All classes that will be serialized have to be registered here
	 */
	@SuppressWarnings("rawtypes")
	protected Map<Integer, ISerializer> register = new HashMap<Integer, ISerializer>();

	/**
	 * Instantiates a new message serializer with no registered serializers.
	 */
	private MessageSerializer() {
	}

	/** The instance. */
	private static MessageSerializer instance;

	/**
	 * Gets the header.
	 *
	 * @param inBytes the in bytes
	 * @return the header
	 */
	protected byte[] getHeader(byte[] inBytes) {
		byte[] bytes = new byte[HEADER_LENGTH];
		System.arraycopy(inBytes, 0, bytes, HEADER_LENGTH, bytes.length);
		return bytes;
	}

	/**
	 * Gets the body.
	 *
	 * @param inBytes the in bytes
	 * @return the body
	 */
	protected byte[] getBody(byte[] inBytes) {
		byte[] bytes = new byte[inBytes.length - HEADER_LENGTH];
		System.arraycopy(inBytes, HEADER_LENGTH, bytes, 0, bytes.length);
		return bytes;
	}

	/**
	 * Deserialize.
	 *
	 * @param bytes    the bytes
	 * @param hashCode the clazz
	 * @return the object
	 * @throws IOException                 Signals that an I/O exception has
	 *                                     occurred.
	 * @throws SerializerNotFoundException the serializer not found exception
	 */
	@SuppressWarnings("rawtypes")
	public Object deserialize(byte[] bytes, int hashCode) throws IOException, SerializerNotFoundException {
		ISerializer serializer = register.get(hashCode);
		if (serializer == null)
			throw new SerializerNotFoundException(
					"Serializer not found. Register a serializer for it on the server and the client before trying to deserialize an object of that type.");
		return serializer.deserialize(bytes);
	}

	/**
	 * Register serializer.
	 *
	 * @param clazz      the clazz
	 * @param serializer the serializer
	 */
	@SuppressWarnings("rawtypes")
	public void register(Class clazz, ISerializer serializer) {
		register.put(clazz.getName().hashCode(), serializer);
	}

	/**
	 * Gets the serializer.
	 *
	 * @param clazz the clazz
	 * @return the serializer
	 */
	@SuppressWarnings("rawtypes")
	public ISerializer getSerializer(Class clazz) throws SerializerNotFoundException {
		ISerializer serializer = register.get(clazz.getName().hashCode());
		if (serializer == null) {
			throw new SerializerNotFoundException(
					"Serializer not found. Register a serializer of it's type for it on the server and the client before trying to serialize an object.");
		}
		return serializer;
	}

	/**
	 * Serialize.
	 *
	 * @param object   the object
	 * @param hashCode the hash code
	 * @return the byte[]
	 * @throws SerializerNotFoundException the serializer not found exception
	 * @throws IOException                 Signals that an I/O exception has
	 *                                     occurred.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] serialize(Object object, int hashCode) throws SerializerNotFoundException, IOException {

		ISerializer serializer = register.get(hashCode);
		if (serializer == null) {
			throw new SerializerNotFoundException(
					"Serializer not found. Register a serializer of it's type for it on the server and the client before trying to serialize an object.");
		}
		return serializer.serialize(object);
	}

	/**
	 * Gets the header.
	 *
	 * @param messageBody the message body
	 * @param clazz       the clazz
	 * @return the header
	 */
	@SuppressWarnings("rawtypes")
	public byte[] getHeader(byte[] messageBody, Class clazz) {
		byte[] header = new byte[HEADER_LENGTH];
		Utils.addIntToByteArray(messageBody.length, header, 0);
		Utils.addIntToByteArray(clazz.getName().hashCode(), header, 4);
		return header;
	}

	/**
	 * Gets the single instance of MessageSerializer.
	 *
	 * @return single instance of MessageSerializer
	 */
	public static MessageSerializer getInstance() {
		if (instance == null) {
			instance = new MessageSerializer();
		}
		return instance;
	}

}
