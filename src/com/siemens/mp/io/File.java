package com.siemens.mp.io;

import java.io.IOException;

public class File {
	public static final int INSIDE_STORAGE_PATH = 1;
	public static final int OUTSIDE_STORAGE_PATH = 0;
	public static final String STORAGE_DRIVE = "a:";
	
	public File() {
		System.err.println("com.siemens.mp.io.File not implemented.");
	}

	public static int checkFileName(String fileName) {
		return 0;
	}

	public static int debugWrite(String fileName, String infoString) {
		return 0;
	}

	public int open(String fileName) throws IOException {
		return 0;
	}

	public static int exists(String fileName) {
		return 0;
	}

	public int seek(int fileDescriptor, int seekpos) throws IOException {
		return 0;
	}

	public int length(int fileDescriptor) throws IOException {
		return 0;
	}

	public int close(int fileDescriptor) throws IOException {
		return 0;
	}

	public int write(int fileDescriptor, byte[] buf, int offset, int numBytes) throws IOException {
		return 0;
	}

	public int read(int fileDescriptor, byte[] buf, int offset, int numBytes) throws IOException {
		return 0;
	}

	public static int delete(String fileName) throws IOException {
		return 0;
	}

	public static int spaceAvailable() throws IOException {
		return 0;
	}

	public static int rename(String source, String dest) throws IOException {
		return 0;
	}

	public static int copy(String source, String dest) throws IOException {
		return 0;
	}

	public static boolean isDirectory(String pathName) throws IOException {
		return false;
	}

	public static String[] list(String pathName) throws IOException {
		return new String[]{};
	}

	public static String buildPath(String fileName) {
		return "";
	}

	public static void truncate(int fileDescriptor, int size) throws IOException {
		
	}
}