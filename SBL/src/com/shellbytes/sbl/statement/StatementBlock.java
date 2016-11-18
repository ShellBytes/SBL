package com.shellbytes.sbl.statement;

import java.util.ArrayList;

import com.shellbytes.sbl.environment.Environment;

public class StatementBlock extends Statement {

	private ArrayList<Statement> statements;
	
	public StatementBlock() {
		super(null, StatementType.BLOCK);
		statements = new ArrayList<>();
	}
	
	public void addStatement(Statement s) {
		statements.add(s);
	}
	
	public void addStatements(Statement... statements) {
		for (Statement s : statements)
			addStatement(s);
	}

	public void addStatements(ArrayList<Statement> statements) {
		this.statements.addAll(statements);
	}
	
	@Override
	public void exec(Environment env) {
		for (Statement s : statements) {
			s.exec(env);
		}
	}
}
