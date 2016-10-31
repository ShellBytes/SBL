package com.shellbytes.sbl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IO {

	public static String readTextFile(String fname) {
		
		File in = new File(fname);
		Scanner scanner;
		try {
			scanner = new Scanner(in);
		} catch (FileNotFoundException e) {
			Messages.err("FileNotFoundException on reading file " + fname, e);
			return null;
		}
		
		scanner.useDelimiter("\\Z");
		String content = scanner.next();
		scanner.close();
		return content;
	}
	
	private static void writeTextFile(String fname, String contents, boolean append) {
		
		File out = new File(fname);
		PrintWriter ostream;
		try {
			out.createNewFile();
			ostream = new PrintWriter(new FileOutputStream(out, append));
		} catch (IOException e) {
			Messages.err("IOException on writing to file " + fname, e);
			return;
		}
		
		ostream.println(contents);
		ostream.close();
	}
	
	public static void writeTextFile(String fname, String contents) {
		writeTextFile(fname, contents, false);
	}
	
	public static void appendTextFile(String fname, String contents) {
		writeTextFile(fname, contents, true);
	}
}
