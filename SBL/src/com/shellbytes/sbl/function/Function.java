package com.shellbytes.sbl.function;

import com.shellbytes.sbl.Messages;
import com.shellbytes.sbl.environment.Environment;
import com.shellbytes.sbl.variable.Variable;

public abstract class Function {
	
	public abstract void exec(Environment env, Variable... params);
	
	public void assertParamLength(Variable[] params, int count) {
		assureParamLength(params, count, count);
	}
	
	public void assureParamLength(Variable[] params, int min, int max) {
		if (params.length < min || params.length > max) {
			Messages.err("Incorrect number of parameters for the function 'print': 1 required but "
					+ Integer.toString(params.length) + " given.");
		}
	}
}
