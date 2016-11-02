package com.shellbytes.sbl.token;

public class Token {

	public enum TokenType {
		IDENTIFIER
	}
	
	private String contents;
	
	private int line;
	private int column;
	private String fname;
	
	private TokenType type;
	
	public Token(String contents, int line, int column, String fname, TokenType type) {
		this.contents = contents;
		this.line = line;
		this.column = column;
		this.fname = fname;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "'" + contents + "'" + " at line "
				+ Integer.toString(line) + " column "
				+ Integer.toString(column) + " in file " + fname;
	}

	public String getContents() {
		return contents;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public String getFname() {
		return fname;
	}

	public TokenType getType() {
		return type;
	}
}
