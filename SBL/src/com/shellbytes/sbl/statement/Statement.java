package com.shellbytes.sbl.statement;

import java.util.ArrayList;

import com.shellbytes.sbl.Messages;
import com.shellbytes.sbl.environment.Environment;
import com.shellbytes.sbl.token.Token;
import com.shellbytes.sbl.variable.Variable;

public class Statement {

	public enum StatementType {
		NONE,
		FUNCTION,
		ASSIGNMENT,
		BLOCK
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
		
		// EXECUTE FUNCTION
		case FUNCTION:
			Variable param;
			switch (tokens.get(2).getType()) {
			
			case IDENTIFIER:
				param = env.getVar(tokens.get(2).getContents(), tokens.get(2).getLine(), tokens.get(2).getColumn(), tokens.get(2).getFname());
				break;
				
			case STRING:
				param = new Variable(tokens.get(2).getContents());
				break;
				
			default:
				Messages.err("A function can only take parameters of IDENTIFIERs and STRINGs, a " + type.toString() + " was given.");
				return;
			}
			env.execFunction(tokens.get(0).getContents(), param);
			break;
		
		// EXECUTE ASSIGNMENT
		case ASSIGNMENT:
			if (env.varExists(tokens.get(0).getContents()))
				env.getVar(tokens.get(0).getContents(), tokens.get(0).getLine(), tokens.get(0).getColumn(), tokens.get(0).getFname()).setContents(tokens.get(2).getContents());
			else
				env.addVar(tokens.get(0).getContents(), new Variable(tokens.get(2).getContents()));
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
