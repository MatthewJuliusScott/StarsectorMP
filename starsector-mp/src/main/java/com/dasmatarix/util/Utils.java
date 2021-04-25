
package com.dasmatarix.util;

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
}
