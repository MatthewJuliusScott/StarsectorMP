
package com.dasmatarix.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
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
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
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
			Reflections reflections = new Reflections("com.fs.starfarer", new SubTypesScanner(false));
			Set<Class<? extends DoNotObfuscate>> doNotObfuscateTypes = reflections.getSubTypesOf(DoNotObfuscate.class);
			for (Class clazz : doNotObfuscateTypes) {
				JCodeModel codeModel = new JCodeModel();
				JPackage jPackage = codeModel._package("com.dasmatarix.multiplayer.serializer");
				int count = 0;
				try {
					String className = clazz.getName();
					if (className.lastIndexOf('.') > -1) {
						className = className.replace('$', '.');
						className = className.substring(className.lastIndexOf('.') + 1);
					}
					System.out.println(
							"Plugin output: Class name " + clazz.getName() + ", creating " + className + "Serializer");
					JDefinedClass jdefinedClass = jPackage._class(className + "Serializer");
					Objenesis objenesis = new ObjenesisStd();
					Object bean = objenesis.newInstance(clazz);
					PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz, Object.class)
							.getPropertyDescriptors();

					// add the imports
					JClass jObjenesis = codeModel.directClass("org.objenesis.Objenesis");
					JClass jObjenesisStd = codeModel.directClass("org.objenesis.ObjenesisStd");
					JClass jClazz = codeModel.directClass(clazz.getName());
					
					for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
						try {
							if (clazz != null && propertyDescriptor != null
									&& propertyDescriptor.getReadMethod() != null) {
								propertyDescriptor.getReadMethod().invoke(bean);
								System.out.println("    " + propertyDescriptor.getName());
								count++;
							}
						} catch (Exception | ExceptionInInitializerError | NoClassDefFoundError e2) {
							// do nothing
						}

					}
					if (count > 0) {
						JMethod serializeMethod = jdefinedClass.method(JMod.PUBLIC, byte[].class, "serialize");
						serializeMethod.body().directStatement("return new byte[0];");
						JMethod deserializeMethod = jdefinedClass.method(JMod.PUBLIC, clazz, "deserialize");
						deserializeMethod.body().directStatement("Objenesis objenesis = new ObjenesisStd();\r\n"
								+ "return (" + className + ")objenesis.newInstance(" + className + ".class);");
					}
				} catch (IllegalArgumentException | IntrospectionException | NoClassDefFoundError | SecurityException
						| ExceptionInInitializerError | InstantiationError | JClassAlreadyExistsException e1) {
					e1.printStackTrace();
					continue;
				}
				// Generate the code
				if (count > 0) {
					codeModel.build(new File("src/main/java/"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
