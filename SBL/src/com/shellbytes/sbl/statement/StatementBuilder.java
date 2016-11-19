package com.shellbytes.sbl.statement;

import java.util.ArrayList;

import com.shellbytes.sbl.Messages;
import com.shellbytes.sbl.statement.Statement.StatementType;
import com.shellbytes.sbl.token.Token;
import com.shellbytes.sbl.token.Token.TokenType;

public class StatementBuilder {

	public static ArrayList<Statement> build(ArrayList<Token> tokens) {
		
		ArrayList<Token> currentTokens = new ArrayList<>();
		ArrayList<Statement> statements = new ArrayList<>();
				
		for (int i=0; i<tokens.size(); i++) {
			
			Token currentToken = tokens.get(i);
			currentTokens.add(currentToken);
			
			if (currentToken.getType() != TokenType.SEMICOLON)
				continue;
			
			StatementType currentType = StatementMatcher.match(currentTokens); 
			switch (currentType) {
			
			case FUNCTION:
			case ASSIGNMENT:
				statements.add(new Statement(currentTokens, currentType));
				currentTokens = new ArrayList<>();
				break;
				
			default:
				String str = "Invalid statement: ";
				for (Token tk : currentTokens)
					str += "\n" + tk.toString();
				str += "\nMaybe this is two statements without a semicolon between them?";
				Messages.err(str);
				break;
			}
		}
		
		if (!currentTokens.isEmpty()) {
			String str = "Unexpected EOF: token list not empty: ";
			for (Token tk : tokens)
				str += "\n" + tk.toString();
			Messages.err(str);
		}
		
		return statements;
	}
}
