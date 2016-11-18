package com.shellbytes.sbl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import com.shellbytes.sbl.environment.Environment;
import com.shellbytes.sbl.statement.Statement;
import com.shellbytes.sbl.statement.StatementBlock;
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
		
		Instant postRead = Instant.now();
		Messages.log("Tokenising file...");
		ArrayList<Token> tokens = Tokeniser.tokenise(code, fname);
		
		Instant postTokenise = Instant.now();
		Messages.log("Building into statements...");
		ArrayList<Statement> statements = StatementBuilder.build(tokens);
		StatementBlock mainBlock = new StatementBlock();
		mainBlock.addStatements(statements);
		
		Instant postStatement = Instant.now();
		Messages.log("Instantiating environment...\n");
		Environment env = new Environment();
		
		Instant end = Instant.now();
		Messages.line();
		Messages.log("Reading took " + Duration.between(start, postRead).toMillis() + "ms");
		Messages.log("Tokenising took " + Duration.between(postRead, postTokenise).toMillis() + "ms");
		Messages.log("Statement Building took " + Duration.between(postTokenise, postStatement).toMillis() + "ms");
		Messages.log("Environment Instantiation took " + Duration.between(postStatement, end).toMillis() + "ms");
		Messages.line();
		Messages.log("Full build finished in " + Duration.between(start, end).toMillis() + "ms");
		Messages.line();
		
		Messages.log("-----Running Program-----\n");
		
		mainBlock.exec(env);
		
		Messages.line();
		Messages.log("-----Program Finished-----");
	}
}