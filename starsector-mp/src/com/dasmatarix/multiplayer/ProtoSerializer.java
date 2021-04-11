
package com.dasmatarix.multiplayer;

import com.fs.starfarer.campaign.fleet.CargoData;

import io.protostuff.GraphIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * The Class Serializer.
 */
public class ProtoSerializer extends CustomSerializer {
	
	static {
		System.setProperty("protostuff.runtime.always_use_sun_reflection_factory", "true");
		System.setProperty("protostuff.runtime.preserve_null_elements", "true");
		System.setProperty("protostuff.runtime.morph_collection_interfaces", "true");
		System.setProperty("protostuff.runtime.morph_map_interfaces", "true");
		System.setProperty("protostuff.runtime.morph_non_final_pojos", "true");
		System.setProperty("protostuff.runtime.collection_schema_on_repeated_fields", "true");
	}
	
	public ProtoSerializer() {
		buffer = LinkedBuffer.allocate(8192);
	}
		
	public static void main(String[] args) throws Exception {
		CustomSerializer serializer = new ProtoSerializer();
		CargoData expected = new CargoData(true);
		expected.getCredits().add(12345F);
		byte[] bytes = serializer.serialize(expected, CargoData.class);
		System.out.println("Serialized actual: " + Utils.bytesToHexString(bytes));
		CargoData actual = (CargoData) serializer.deserialize(bytes, CargoData.class);
		System.out.println(expected.getCredits().get() + " equals " + actual.getCredits().get() + ", " + (expected.getCredits().get() == actual.getCredits().get()));
	}
	
	/**
	 * Deserialize.
	 *
	 * @param bytes the bytes
	 * @param clazz the clazz
	 * @return the object
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Object deserialize(byte[] bytes, Class clazz) {
		Schema schema = RuntimeSchema.getSchema(clazz);
		Object parsedObject = schema.newMessage();
		GraphIOUtil.mergeFrom(bytes, parsedObject, schema);
		return parsedObject;
	}

	/** The buffer. */
	private LinkedBuffer buffer;

	/**
	 * Serialize.
	 *
	 * @param object the object
	 * @param clazz  the clazz
	 * @return the byte[]
	 * @throws ClassNotFoundException the class not found exception
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public byte[] serialize(Object object,
	        Class clazz)
	        throws ClassNotFoundException {
		
		// confirm this class has been registered
		if (classRegister.get(clazz.getName().hashCode()) == null) {
			throw new ClassNotFoundException(
			        "Did you register " + clazz.getName()
			                + " with the Serializer class register map?");
		}

		final byte[] bytes;
		Schema schema = RuntimeSchema.getSchema(clazz);
		try {
			bytes = GraphIOUtil.toByteArray(object, schema, buffer);
		} finally {
			buffer.clear();
		}

		// cannot have a total packet size exceeding Integer.MAX_VALUE including
		// the header. Please don't do this anyway, why would you packet be so
		// big?
		if (HEADER_LENGTH + bytes.length > Integer.MAX_VALUE)
			throw new IllegalArgumentException(
			        "The length of bytes cannot exceed " + Integer.MAX_VALUE);

		return bytes;
	}
}
