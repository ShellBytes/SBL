package com.shellbytes.sbl.token;

import java.util.ArrayList;

import com.shellbytes.sbl.Messages;
import com.shellbytes.sbl.token.Token.TokenType;

public class Tokeniser {

	public static final char[] DELIMITERS = {
			' ', '\t', '\n', '\r', '\0',
			'(', ')', ';',
			'"', '\\',
			'#'
	};
	
	public static ArrayList<Token> tokenise(String code, String fname) {
		
		ArrayList<Token> tokens = new ArrayList<>();
		
		String currentToken = "";
		
		code += '\0';  // EOF marker
		char[] codeChars = code.toCharArray();
		
		int line = 1;
		int column = 1;
		
		boolean parsingString = false;
		boolean lineIsComment = false;
		int commentLine = line;
		
		char c;
		boolean cIsDelimiter;
		
		for (int i=0; i<codeChars.length; i++) {
			
			c = codeChars[i];
			
			if (!parsingString && !lineIsComment) {
			
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
						switch (currentToken) {
						case "print":
							tokens.add(new Token(currentToken, line, column, fname, TokenType.KEYWORD));
							break;
						default:
							tokens.add(new Token(currentToken, line, column, fname, TokenType.IDENTIFIER));		
						}
					}
					
					if (c == '(') {
						tokens.add(new Token("(", line, column, fname, TokenType.PARENTH_OPEN));
					} else if (c == ')') {
						tokens.add(new Token(")", line, column, fname, TokenType.PARENTH_CLOSE));
					} else if (c == ';') {
						tokens.add(new Token(";", line, column, fname, TokenType.SEMICOLON));
					} else if (c == '"') {
						parsingString = true;
					} else if (c == '#') {
						lineIsComment = true;
						commentLine = line;
					}
					
					currentToken = "";
				}
				
			} else if (parsingString) {
				
				if (c == '"') {
					tokens.add(new Token(currentToken, line, column, fname, TokenType.STRING));
					parsingString = false;
				} else if (c == '\\') {
					i++;
					char escaped = codeChars[i];
					switch (escaped) {
					case 'n':
						currentToken += '\n';
						break;
					case '"':
						currentToken += '"';
						break;
					default:
						Messages.err("Invalid escape sequence: \\" + escaped);
					}
				} else {
					currentToken += c;
				}
				
				if (!parsingString) currentToken = "";
			
			} else if (lineIsComment) {
				if (line != commentLine) {
					lineIsComment = false;
					i--;
				}
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
