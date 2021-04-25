
package com.dasmatarix.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.fs.util.DoNotObfuscate;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;

/**
 * The Class Utils.
 */
public class Utils {

	/** The Constant HEX_ARRAY. */
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	/**
	 * Bytes to hex string.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	public static String bytesToHexString(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	/**
	 * Hex string to bytes. s must be an even-length string.
	 *
	 * @param s the s
	 * @return the byte[]
	 */
	public static byte[] hexStringToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	/**
	 * Adds an int to byte array.
	 *
	 * @param i     the i
	 * @param bytes the bytes
	 */
	public static void intToByteArray(int i) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (i >>> 24);
		bytes[1] = (byte) (i >>> 16);
		bytes[2] = (byte) (i >>> 8);
		bytes[3] = (byte) i;
	}

	/**
	 * Adds an int to byte array.
	 *
	 * @param i          the i
	 * @param startIndex the start index
	 */
	public static void addIntToByteArray(int i, byte[] bytes, int startIndex) {
		bytes[startIndex] = (byte) (i >>> 24);
		bytes[startIndex + 1] = (byte) (i >>> 16);
		bytes[startIndex + 2] = (byte) (i >>> 8);
		bytes[startIndex + 3] = (byte) i;
	}

	public static void main(String[] args) throws Exception {
		try {
			
			JCodeModel codeModel = new JCodeModel();
			JPackage jp = codeModel._package("com.dasmatarix.multiplayer.serializer");
			
			Reflections reflections = new Reflections("com.fs.starfarer", new SubTypesScanner(false));
			Set<Class<? extends DoNotObfuscate>> doNotObfuscateTypes = reflections.getSubTypesOf(DoNotObfuscate.class);
			for (Class clazz : doNotObfuscateTypes) {
				
				JDefinedClass jc = jp._class("Generated");
				
				System.out.println("Plugin output: Class name " + clazz.getName());
				try {
					Objenesis objenesis = new ObjenesisStd(); // or ObjenesisSerializer
					Object bean = objenesis.newInstance(clazz);
					PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz, Object.class)
							.getPropertyDescriptors();
					for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
						try {
							if (clazz != null && propertyDescriptor != null
									&& propertyDescriptor.getReadMethod() != null) {
								propertyDescriptor.getReadMethod().invoke(bean);
								System.out.println("    " + propertyDescriptor.getName());
								
							}
						} catch (Exception | ExceptionInInitializerError | NoClassDefFoundError e2) {
							// do nothing
						}

					}
				} catch (IntrospectionException | NoClassDefFoundError | SecurityException | ExceptionInInitializerError | InstantiationError e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
