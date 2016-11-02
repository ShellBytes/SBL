package com.shellbytes.sbl;

import java.util.ArrayList;

import com.shellbytes.sbl.token.Token;
import com.shellbytes.sbl.token.Tokeniser;

public class Main {
	
	// Main method
	public static void main(String[] args) {
		Messages.init();
		
		Messages.log("SBL Interpreter Started");
		
		String fname = "main.sbl";
		String code = IO.readTextFile(fname);
		ArrayList<Token> tokens = Tokeniser.tokenise(code, fname);
		for (Token tk : tokens) {
			Messages.log(tk.toString());
		}
	}
}