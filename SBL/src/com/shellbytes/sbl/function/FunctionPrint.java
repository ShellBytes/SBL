package com.shellbytes.sbl.function;

import com.shellbytes.sbl.Messages;
import com.shellbytes.sbl.environment.Environment;
import com.shellbytes.sbl.variable.Variable;
import com.shellbytes.sbl.variable.Variable.VariableType;

public class FunctionPrint extends Function {

	public String getName() {
		return "print";
	}
	
	@Override
	public void exec(Environment env, Variable... params) {
		assertParamLength(params, 1);
		params[0].assertType(VariableType.STRING);
		Messages.out(params[0].stringVal);
	}
}
