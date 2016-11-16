package com.shellbytes.sbl.environment;

import java.util.Map;
import java.util.TreeMap;

import com.shellbytes.sbl.function.Function;
import com.shellbytes.sbl.function.FunctionPrint;
import com.shellbytes.sbl.variable.Variable;

public class Environment {

	private Map<String, Function> functions;
	
	public Environment() {
		functions = new TreeMap<>();
		functions.put("print", new FunctionPrint());
	}
	
	public void execFunction(String name, Variable... params) {
		functions.get(name).exec(this, params);
	}
}
