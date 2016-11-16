package com.shellbytes.sbl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import com.shellbytes.sbl.environment.Environment;
import com.shellbytes.sbl.statement.Statement;
import com.shellbytes.sbl.statement.StatementBuilder;
import com.shellbytes.sbl.token.Token;
import com.shellbytes.sbl.token.Tokeniser;

public class Main {
	
	// Main method
	public static void main(String[] args) {
		Messages.init();
		
		Instant start = Instant.now();
		Messages.log("SBL Interpreter Started\n");
		
		String fname = "main.sbl";
		Messages.log("Reading " + fname + "...");
		String code = IO.readTextFile(fname);
		
		Messages.log("Tokenising file...");
		ArrayList<Token> tokens = Tokeniser.tokenise(code, fname);
		
		Messages.log("Building into statements...");
		ArrayList<Statement> statements = StatementBuilder.build(tokens);
		
		Messages.log("Instantiating environment...\n");
		Environment env = new Environment();
		
		Instant end = Instant.now();
		Messages.log("Building finished in " + Duration.between(start, end).toMillis() + "ms");
		
		Messages.log("-----Running Program-----\n");
		
		for (Statement st : statements) {
			st.exec(env);
		}
		
		Messages.line();
		Messages.log("-----Program Finished-----");
	}
}