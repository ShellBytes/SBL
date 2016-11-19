package com.shellbytes.sbl.environment;

import java.util.Map;
import java.util.TreeMap;

import com.shellbytes.sbl.Messages;
import com.shellbytes.sbl.function.Function;
import com.shellbytes.sbl.function.FunctionPrint;
import com.shellbytes.sbl.variable.Variable;

public class Environment {

	private Map<String, Function> functions;
	private Map<String, Variable> variables;
	
	public Environment() {
		functions = new TreeMap<>();
		functions.put("print", new FunctionPrint());
		
		variables = new TreeMap<>();
	}
	
	public void execFunction(String name, Variable... params) {
		functions.get(name).exec(this, params);
	}
	
	public void addVar(String key, Variable value) {
		if (variables.containsKey(key)) {
			Messages.err("Cannot add variable " + key + ": it already exists.");
		}
		if (functions.containsKey(key)) {
			Messages.err("Cannot add variable " + key + ": it is a function.");
		}
		
		variables.put(key, value);
	}
	
	public boolean varExists(String name) {
		return variables.containsKey(name);
	}
	
	/**
	 * @return A mutable variable.
	 */
	public Variable getVar(String name, int line, int column, String fname) {
		if (variables.containsKey(name)) {
			return variables.get(name);
		} else {
			Messages.err("Variable " + name + " doesn't exist at line "
					+ Integer.toString(line) + " column "
					+ Integer.toString(column) + " in file " + fname);
			return null;
		}
	}
}
