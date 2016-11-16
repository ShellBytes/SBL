package com.shellbytes.sbl.variable;

public class Variable {

	public enum VariableType {
		STRING,
	}
	
	public String stringVal = "";
	
	private VariableType type = VariableType.STRING;
	
	public Variable(String val) {
		stringVal = val;
	}
	
	public VariableType getType() {
		return type;
	}
	
	/**
	 * @return Whether the type of this variable matches the parameter.
	 */
	public boolean assertType(VariableType type) {
		return type == this.type;
	}
	
	public Object getContents() {
		switch (type) {
		case STRING:
			return stringVal;
		}
		assert false : "Undefined variable type: " + type.toString();
		return null;
	}
}
