package com.shellbytes.sbl.statement;

import java.util.ArrayList;

import com.shellbytes.sbl.statement.Statement.StatementType;
import com.shellbytes.sbl.token.Token;

public class StatementBuilder {

	public static ArrayList<Statement> build(ArrayList<Token> tokens) {
		
		ArrayList<Token> currentTokens = new ArrayList<>();
		ArrayList<Statement> statements = new ArrayList<>();
				
		for (int i=0; i<tokens.size(); i++) {
			
			currentTokens.add(tokens.get(i));
			
			switch (StatementMatcher.match(currentTokens)) {
			case FUNCTION:
				statements.add(new Statement(currentTokens, StatementType.FUNCTION));
				currentTokens = new ArrayList<>();
				break;
			default:
				break;
			}
		}
		
		return statements;
	}
}
