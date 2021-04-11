
package com.dasmatarix.multiplayer;

import java.util.HashMap;
import java.util.Map;

import com.fs.starfarer.campaign.fleet.CampaignFleet;
import com.fs.starfarer.campaign.fleet.CargoData;
import com.fs.starfarer.campaign.fleet.FleetData;

/**
 * The Class CustomSerializer.
 *
 * @param <T> the generic type
 */
public abstract class CustomSerializer<T> {

	/** The Constant HEADER_LENGTH. */
	public static final int HEADER_LENGTH = 2 * (Integer.SIZE / 8);

	/**
	 * Gets the header.
	 *
	 * @param inBytes the in bytes
	 * @return the header
	 */
	protected static byte[] getHeader(byte[] inBytes) {
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
	protected static byte[] getBody(byte[] inBytes) {
		byte[] bytes = new byte[inBytes.length - HEADER_LENGTH];
		System.arraycopy(inBytes, HEADER_LENGTH, bytes, 0, bytes.length);
		return bytes;
	}

	/**
	 * The class set. All classes that will be serialized have to be registered
	 * here
	 */
	@SuppressWarnings("rawtypes")
	protected static Map<Integer, Class> classRegister = new HashMap<Integer, Class>();
	static {
		classRegister.put(FleetData.class.getName().hashCode(),
		        FleetData.class);
		classRegister.put(CargoData.class.getName().hashCode(),
		        CargoData.class);
		classRegister.put(CampaignFleet.class.getName().hashCode(),
		        CampaignFleet.class);
	}

	/**
	 * Deserialize.
	 *
	 * @param bytes  the bytes
	 * @param class1 the class 1
	 * @return the object
	 */
	protected abstract Object deserialize(byte[] bytes, Class<Object> class1);

	/**
	 * Serialize.
	 *
	 * @param object the object
	 * @param clazz  the clazz
	 * @return the byte[]
	 * @throws ClassNotFoundException the class not found exception
	 */
	protected abstract byte[] serialize(Object object, Class clazz)
	        throws ClassNotFoundException;

	/**
	 * Gets the header.
	 *
	 * @param messageBody the message body
	 * @param clazz       the clazz
	 * @return the header
	 */
	protected byte[] getHeader(byte[] messageBody, Class clazz) {
		byte[] header = new byte[HEADER_LENGTH];
		Utils.addIntToByteArray(messageBody.length, header, 0);
		Utils.addIntToByteArray(clazz.getName().hashCode(), header, 4);
		return header;
	}

}
