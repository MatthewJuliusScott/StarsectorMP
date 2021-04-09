
package com.dasmatarix.multiplayer;

import java.util.HashMap;
import java.util.Map;

import com.fs.starfarer.campaign.fleet.CampaignFleet;
import com.fs.starfarer.campaign.fleet.CargoData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * The Class Serializer.
 */
public class Serializer {
	
	private static XStream xStream = createXStream();
	
	/**
	 * Gets the x stream.
	 *
	 * @return the x stream
	 */
	public static XStream createXStream() {
		XStream xStream = new XStream(new StaxDriver());
		xStream.omitField(CargoData.class, "origSource");
		xStream.omitField(CargoData.class, "carryingFleet");
		xStream.omitField(CargoData.class, "mothballedShips");
		return xStream;
	}

	/** The class set. All classes that will be serialized have to be registered here*/
	@SuppressWarnings("rawtypes")
	public static Map<Integer, Class> classRegister = new HashMap<Integer, Class>();
	static {
		classRegister.put(CampaignFleet.class.getName().hashCode(),
		        CampaignFleet.class);
		classRegister.put(CargoData.class.getName().hashCode(), CargoData.class);
	}

	/**
	 * Gets the class from set.
	 *
	 * @param key the key
	 * @return the class from set
	 */
	public static Class getClassFromSet(Integer key) {
		return classRegister.get(key);
	}
	
	/**
	 * Deserialize.
	 *
	 * @param bytes the bytes
	 * @return the object
	 */
	public static Object deserialize(byte[] bytes) {
		return xStream.fromXML(new String(bytes));
	}

	public static byte[] serialize(Object object, @SuppressWarnings("rawtypes") Class clazz) throws ClassNotFoundException {
		
		// confirm this class has been registered
		if (classRegister.get(clazz.getName().hashCode()) == null) {
			throw new ClassNotFoundException("Did you register " + clazz.getName() + " with the Serializer class register map?");
		}
		
		String s = (xStream.toXML(object));
		
		byte[] compressedBytes = Utils.gzipCompress(s.getBytes());
		int headerLength = 2 * (Integer.SIZE / 8);
		// cannot have a total packet size exceeding Integer.MAX_VALUE including
		// the header. Please don't do this anyway, why would you packet be so
		// big?
		if (headerLength + compressedBytes.length > Integer.MAX_VALUE)
			throw new IllegalArgumentException(
			        "The length of bytes cannot exceed " + Integer.MAX_VALUE);

		// create a new byte array of the same length + the header
		byte[] bytes = new byte[headerLength + compressedBytes.length];

		int index = 0;
		// copy the input byte array length as the first 4 bytes
		Utils.addIntToByteArray(compressedBytes.length, bytes, index);
		index += 4;

		// copy the hashCode as the next 4 bytes
		Utils.addIntToByteArray(clazz.getName().hashCode(), bytes, index);

		// copy the input array in to the new byte array already containing the
		// header
		System.arraycopy(compressedBytes, 0, bytes, (Integer.SIZE / 8) * 2,
		        compressedBytes.length);

		return bytes;
	}
}
