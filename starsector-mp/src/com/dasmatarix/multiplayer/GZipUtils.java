
package com.dasmatarix.multiplayer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.util.FastByteArrayOutputStream;

public class GZipUtils {

	/**
	 * Gzip compress.
	 *
	 * @param uncompressedData the uncompressed data
	 * @return the byte[]
	 */
	public static byte[] gzipCompress(byte[] uncompressedData) {
		byte[] result = new byte[]{};
		try (FastByteArrayOutputStream bos = new FastByteArrayOutputStream(
		        uncompressedData.length);
		        GZIPOutputStream gzipOS = new GZIPOutputStream(bos)) {
			gzipOS.write(uncompressedData);
			// You need to close it before using bos
			gzipOS.close();
			result = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Gzip uncompress.
	 *
	 * @param compressedData the compressed data
	 * @return the byte[]
	 */
	public static byte[] gzipUncompress(byte[] compressedData) {
		byte[] result = new byte[]{};
		try (ByteArrayInputStream bis = new ByteArrayInputStream(
		        compressedData);
		        FastByteArrayOutputStream bos = new FastByteArrayOutputStream();
		        GZIPInputStream gzipIS = new GZIPInputStream(bis)) {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = gzipIS.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			result = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
