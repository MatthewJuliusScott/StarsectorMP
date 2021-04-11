package com.dasmatarix.multiplayer.serializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.unpacker.Unpacker;
import static org.msgpack.template.Templates.tList;
import static org.msgpack.template.Templates.tMap;
import static org.msgpack.template.Templates.TString;

import com.dasmatarix.multiplayer.model.ExampleObject;
import com.dasmatarix.util.FastByteArrayOutputStream;

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
        expected.setIa(new int[] { 1, 2, 3, 4 });
        expected.setDa(new Double[] { 10.5, 20.5 });
        expected.setSa(new String[] { "msg", "pack", "for", "java" });
        expected.setBa(new byte[] { 0x30, 0x31, 0x32 }); // byte array

        // Serialize various types of other reference values
        expected.setWs("MesagePack"); // String object
        expected.setBuf(ByteBuffer.wrap(new byte[] { 0x30, 0x31, 0x32 })); // ByteBuffer object
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
		FastByteArrayOutputStream out = new FastByteArrayOutputStream();
		MessagePack msgpack = new MessagePack();
        Packer packer = msgpack.createPacker(out);

        // Serialize values of primitive types
        packer.write(object.isB()); // boolean value
        packer.write(object.getI()); // int value
        packer.write(object.getD()); // double value

        // Serialize objects of primitive wrapper types
        packer.write(object.getWb());
        packer.write(object.getWi());
        packer.write(object.getWd());

        // Serialize various types of arrays
        packer.write(object.getIa());
        packer.write(object.getDa());
        packer.write(object.getSa());
        packer.write(object.getBa()); // byte array

        // Serialize various types of other reference values
        packer.write(object.getWs()); // String object
        packer.write(object.getBuf()); // ByteBuffer object
        packer.write(object.getBi()); // BigInteger object
        
        // Serialize List object
        List<String> list = new ArrayList<String>();
        list.add("msgpack");
        list.add("for");
        list.add("java");
        packer.write(list); // List object

        // Serialize Map object
        Map<String, String> map = new HashMap<String, String>();
        map.put("sadayuki", "furuhashi");
        map.put("muga", "nishizawa");
        packer.write(map); // Map object
        
        byte[] bytes = out.toByteArray();
		return bytes;
	}

	@Override
	public ExampleObject deserialize(byte[] bytes) throws IOException {
		ExampleObject object = new ExampleObject();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		MessagePack msgpack = new MessagePack();
        Unpacker unpacker = msgpack.createUnpacker(in);

        // to primitive values
        object.setB(unpacker.readBoolean()); // boolean value
        object.setI(unpacker.readInt()); // int value
        object.setD(unpacker.readDouble()); // double value

        // to primitive wrapper value
        object.setWb(unpacker.read(Boolean.class));
        object.setWi(unpacker.read(Integer.class));
        object.setWd(unpacker.read(Double.class));

        // to arrays
        object.setIa(unpacker.read(int[].class));
        object.setDa(unpacker.read(Double[].class));
        object.setSa(unpacker.read(String[].class));
        object.setBa(unpacker.read(byte[].class));

        // to String object, ByteBuffer object, BigInteger object, List object and Map object
        object.setWs(unpacker.read(String.class));
        object.setBuf(unpacker.read(ByteBuffer.class));
        object.setBi(unpacker.read(BigInteger.class));
        
        Template<List<String>> listTmpl = tList(TString);
        Template<Map<String, String>> mapTmpl = tMap(TString, TString);
        
        // to List object
        object.setDstList(unpacker.read(listTmpl));

        // to Map object
        object.setDstMap(unpacker.read(mapTmpl));
        
        return object;
	}

}
