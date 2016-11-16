package com.shellbytes.sbl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Messages {

	private static int MESSAGE_LEVEL = 0;
	private static String fname;
	
	public static enum MessageLevel {
		ERROR,
		WARNING,
		INFO,
		LOG
	}
	
	public static void init() {
		MESSAGE_LEVEL = MessageLevel.LOG.ordinal();
		Date today = new Date();
		fname = "log.txt";
		IO.writeTextFile(fname, "Compilation started at "
				+ new SimpleDateFormat("HH:mm:SS").format(today)
				+ " on "
				+ new SimpleDateFormat("dd/MM/yy").format(today)
				+ ".");
	}
	
	public static void end() {
		
	}
	
	public static void setMessageLevel(MessageLevel level) {
		MESSAGE_LEVEL = level.ordinal();
	}
	
	public static MessageLevel getMessageLevel() {
		return MessageLevel.values()[MESSAGE_LEVEL];
	}
	
	public static void log(String message) {
		message = "     > " + message;
		if (MESSAGE_LEVEL > 2)
			System.out.println(message);
		IO.appendTextFile(fname, message);
	}
	
	public static void info(String message) {
		message = "INFO > " + message;
		if (MESSAGE_LEVEL > 1)
			System.out.println(message);
		IO.appendTextFile(fname, message);
	}
	
	public static void warn(String message) {
		message = "WARN > " + message;
		if (MESSAGE_LEVEL > 0)
			System.out.println(message);
		IO.appendTextFile(fname, message);
	}
	
	public static void err(String message) {
		message = " ERR > " + message;
		System.err.println(message);
		IO.appendTextFile(fname, message);
		System.exit(-1);
	}
	
	public static void out(String message) {
		System.out.println(message);
		IO.appendTextFile(fname, message);
	}
	
	public static void line() {
		out("");
	}
	
	public static void err(String message, Exception e) {
		e.printStackTrace();
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		IO.appendTextFile(fname, sw.toString());
		
		err(message);
	}
}
