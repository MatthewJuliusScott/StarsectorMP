
package com.dasmatarix.multiplayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fs.starfarer.campaign.fleet.CargoData;

/**
 * The Class Serializer.
 */
public class KryoSerializer extends CustomSerializer {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		CustomSerializer serializer = new KryoSerializer();
		CargoData expected = new CargoData(true);
		expected.getCredits().add(12345F);
		byte[] bytes = serializer.serialize(expected, CargoData.class);
		System.out.println("Serialized actual: " + Utils.bytesToHexString(bytes));
		CargoData actual = (CargoData) serializer.deserialize(bytes, CargoData.class);
		System.out.println(expected.getCredits().get() + " equals "
		        + actual.getCredits().get() + ", "
		        + (expected.getCredits().get() == actual.getCredits().get()));
	}

	/**
	 * Deserialize.
	 *
	 * @param bytes the bytes
	 * @param clazz the clazz
	 * @return the object
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object deserialize(byte[] bytes, Class clazz) {
		Kryox kryo = new Kryox();
		Input input = new Input(new ByteArrayInputStream(bytes));
		return kryo.readObject(input, clazz);
	}

	/**
	 * Serialize.
	 *
	 * @param object the object
	 * @param clazz  the clazz
	 * @return the byte[]
	 * @throws ClassNotFoundException the class not found exception
	 */
	public byte[] serialize(Object object,
	        @SuppressWarnings("rawtypes") Class clazz)
	        throws ClassNotFoundException {
		
		// confirm this class has been registered
		if (classRegister.get(clazz.getName().hashCode()) == null) {
			throw new ClassNotFoundException(
			        "Did you register " + clazz.getName()
			                + " with the Serializer class register map?");
		}

		Kryox kryo = new Kryox();
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		Output output = new Output(b);
		kryo.writeObject(output, object);

		byte[] bytes = output.getBuffer();

		// cannot have a total packet size exceeding Integer.MAX_VALUE including
		// the header. Please don't do this anyway, why would you packet be so
		// big?
		if (HEADER_LENGTH + bytes.length > Integer.MAX_VALUE)
			throw new IllegalArgumentException(
			        "The length of bytes cannot exceed " + Integer.MAX_VALUE);

		return bytes;
	}
}
