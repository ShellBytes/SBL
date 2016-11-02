package com.shellbytes.sbl.token;

import java.util.ArrayList;

import com.shellbytes.sbl.token.Token.TokenType;

public class Tokeniser {

	public static final char[] DELIMITERS = { ' ', '\t', '\n', '\0' };
	
	public static ArrayList<Token> tokenise(String code, String fname) {
		
		ArrayList<Token> tokens = new ArrayList<>();
		
		String currentToken = "";
		
		code += '\0';  // EOF marker
		char[] codeChars = code.toCharArray();
		
		int line = 1;
		int column = 1;
		
		char c;
		boolean cIsDelimiter;
		
		for (int i=0; i<codeChars.length; i++) {
			
			c = codeChars[i];
			
			cIsDelimiter = false;
			for (char delimiter : DELIMITERS)
				if (c == delimiter)
					cIsDelimiter = true;
			
			if (!cIsDelimiter) {
				currentToken += c;
			} else {
				if (currentToken.trim() == "") {
					// Do nothing
				} else {
					tokens.add(new Token(currentToken, line, column, fname, TokenType.IDENTIFIER));
				}
				currentToken = "";
			}
			
			if (c == '\n') {
				line++;
				column = 1;
			} else {
				column++;
			}
		}
		
		return tokens;
	}
}
