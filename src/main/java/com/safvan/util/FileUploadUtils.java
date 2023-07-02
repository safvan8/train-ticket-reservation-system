package com.safvan.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUploadUtils {

	// the convertToByteArray method takes a MultipartFile object and converts it to
	// a byte array.
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

}
