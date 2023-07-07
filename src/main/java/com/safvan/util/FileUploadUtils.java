package com.safvan.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * FileUploadUtils class provides utility methods for file upload operations.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
public class FileUploadUtils {

	/**
	 * Convert a MultipartFile object to a byte array.
	 *
	 * @param file the MultipartFile object to convert
	 * @return the byte array representing the file data, or null if the file is
	 *         empty or an error occurs
	 */
	public static byte[] convertToByteArray(MultipartFile file) {
		if (file.isEmpty()) {
			return null;
		}
		try {
			return file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Add other utility methods if needed
}
