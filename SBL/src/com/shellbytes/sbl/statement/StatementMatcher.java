package com.shellbytes.sbl.statement;

import java.util.ArrayList;

import com.shellbytes.sbl.statement.Statement.StatementType;
import com.shellbytes.sbl.token.Token;
import com.shellbytes.sbl.token.Token.TokenType;

public class StatementMatcher {

	public static StatementType match(ArrayList<Token> tokens) {
		
		if (matchFunction(tokens)) return StatementType.FUNCTION;
		if (matchAssignment(tokens)) return StatementType.ASSIGNMENT;
		
		return StatementType.NONE;
	}
	
	/**
	 * A function is defined as:</br>
	 * <code>KEYWORD PARENTH_LEFT tokens PARENTH_RIGHT SEMICOLON</code>
	 * @return Whether this token array matches the definition of a function.
	 */
	public static boolean matchFunction(ArrayList<Token> tokens) {
		
		try {
			
			if (tokens.get(0).getType() != TokenType.KWD_PRINT) return false;
			if (tokens.get(1).getType() != TokenType.PARENTH_OPEN) return false;
			int i = 2;
			while (tokens.get(i++).getType() != TokenType.PARENTH_CLOSE);
			if (tokens.get(i).getType() != TokenType.SEMICOLON) return false;
			
			return true;
			
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/**
	 * An assignment is defined as:</br>
	 * <code>IDENTIFIER EQUALS STRING SEMICOLON</code>
	 * @return Whether this token array matches the definition of a function.
	 */
	public static boolean matchAssignment(ArrayList<Token> tokens) {
		
		try {
			
			if (tokens.get(0).getType() != TokenType.IDENTIFIER) return false;
			if (tokens.get(1).getType() != TokenType.EQUALS) return false;
			if (tokens.get(2).getType() != TokenType.STRING) return false;
			if (tokens.get(3).getType() != TokenType.SEMICOLON) return false;
			
			return true;
			
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
}
