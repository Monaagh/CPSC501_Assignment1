package mypackage;

import java.io.BufferedReader;
import java.io.IOException;

public class Handler {

	public Handler() {
		super();
	}

	public void printInstruction(BufferedReader reader) throws IOException {
		System.out.println("Please press a key to continue.");
		reader.readLine();
		System.out.println("Please choose from the following commands by entering the number");
	}

}