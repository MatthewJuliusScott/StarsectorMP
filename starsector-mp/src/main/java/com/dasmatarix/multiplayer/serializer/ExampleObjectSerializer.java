
package com.dasmatarix.multiplayer.serializer;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dasmatarix.multiplayer.model.ExampleObject;

public class ExampleObjectSerializer implements ISerializer<ExampleObject> {

	public static void main(String[] args) throws Exception {

		ExampleObjectSerializer serializer = new ExampleObjectSerializer();

		ExampleObject expected = new ExampleObject();

		// Serialize values of primitive types
		expected.setB(true); // boolean value
		expected.setI(10); // int value
		expected.setD(10.5); // double value

		// Serialize objects of primitive wrapper types
		expected.setWb(Boolean.TRUE);
		expected.setWi(new Integer(10));
		expected.setWd(new Double(10.5));

		// Serialize various types of arrays
		expected.setIa(new int[]{1, 2, 3, 4});
		expected.setDa(new Double[]{10.5, 20.5});
		expected.setSa(new String[]{"msg", "pack", "for", "java"});
		expected.setBa(new byte[]{0x30, 0x31, 0x32}); // byte array

		// Serialize various types of other reference values
		expected.setWs("MesagePack"); // String object
		expected.setBuf(ByteBuffer.wrap(new byte[]{0x30, 0x31, 0x32})); // ByteBuffer
		                                                                // object
		expected.setBi(BigInteger.ONE); // BigInteger object

		// Serialize List object
		List<String> list = new ArrayList<String>();
		list.add("msgpack");
		list.add("for");
		list.add("java");
		expected.setDstList(list); // List object

		// Serialize Map object
		Map<String, String> map = new HashMap<String, String>();
		map.put("sadayuki", "furuhashi");
		map.put("muga", "nishizawa");
		expected.setDstMap(map); // Map object

		byte[] bytes = serializer.serialize(expected);

		ExampleObject actual = serializer.deserialize(bytes);

		if (expected.equals(actual)) {
			System.out.println("Pass");
			System.out.println(expected.toString());
			System.out.println("Equals");
			System.out.println(actual.toString());
		} else {
			throw new Exception("Fail");
		}

	}

	@Override
	public byte[] serialize(ExampleObject object) throws IOException {
		// TODO implementation
		return null;
	}

	@Override
	public ExampleObject deserialize(byte[] bytes) throws IOException {
		// TODO implementation
		return null;
	}

}
