package com.dasmatarix.multiplayer;

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
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	
	/**
	 * Creates a packet from a byte array, giving it a header
	 *
	 * @param bytes the bytes
	 * @return the byte[]
	 */
	// TODO would it be better to be a ByteBuffer so that I don't have to do the array copies? 
	public static byte[] createPacket(byte[] in) {
		
		int headerLength = (Integer.SIZE / 8);
		// cannot have a total packet size exceeding Integer.MAX_VALUE including the header. Please don't do this anyway, why would you packet be so big? 
		if (in.length + headerLength > Integer.MAX_VALUE) throw new IllegalArgumentException("The length of bytes cannot exceed " + Integer.MAX_VALUE);
		
		// create a new byte array of the same length + the header
		byte[] bytes = new byte[in.length + (Integer.SIZE / 8)];
		
		// copy the input byte array length as the first 4 bytes
		bytes[0] = (byte)(in.length >>> 24);
		bytes[1] = (byte)(in.length >>> 16);
		bytes[2] = (byte)(in.length >>> 8);
		bytes[3] = (byte)in.length;
		
		// copy the input array in to the new byte array already containing the header
		System.arraycopy(in, 0, bytes, (Integer.SIZE / 8), in.length);
		
		return bytes;
	}

}
