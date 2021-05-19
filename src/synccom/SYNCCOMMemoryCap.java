package synccom;

import java.util.ArrayList;
import java.util.List;

public class SYNCCOMMemoryCap {
	static int input;
	static int output;

	public static int getInput() {
		return input;
	}

	public static void setInput(int cinput) {
		input = cinput;
	}

	public static int getOutput() {
		return output;
	}

	public static void setOutput(int coutput) {
		output = coutput;
	}

	public static List<Integer> getCapRegisters(){
		
		return new ArrayList<Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{
				add(input);
				add(output);
			}
		};
	}

}
