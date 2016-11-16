package com.shellbytes.sbl.statement;

import java.util.ArrayList;

import com.shellbytes.sbl.environment.Environment;
import com.shellbytes.sbl.token.Token;
import com.shellbytes.sbl.variable.Variable;

public class Statement {

	public enum StatementType {
		NONE,
		FUNCTION
	}
	
	private ArrayList<Token> tokens;
	private StatementType type;
	
	public Statement(ArrayList<Token> tokens, StatementType type) {
		this.tokens = tokens;
		this.type = type;
	}
	
	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	public void exec(Environment env) {
		switch (type) {
		case FUNCTION:
			env.execFunction(tokens.get(0).getContents(), new Variable(tokens.get(2).getContents()));
			break;
		default:
			break;
		}
	}
	
	@Override
	public String toString() {
		String out = "Statement ";
		for (Token tk : tokens) {
			out += tk.getContents();
		}
		return out;
	}
	
	public StatementType getType() {
		return type;
	}
}
