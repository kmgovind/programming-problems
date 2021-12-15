import java.io.*;
import java.util.*;


public class Diff {
	public static String inputA;
	public static String inputB;
	public static String com1;
	public static String com2;
	public static String com3;
	public static String com4;
	
	//Finds the final common strings and puts in alphabetical order
	public static String common(){
		String values = "";
		for(int i = 0; i < com1.length(); i++) {
			char identifier = com1.charAt(i);
			if((com2.indexOf(identifier) != -1) && (com3.indexOf(identifier) != -1) && (com4.indexOf(identifier) != -1)) {
				if(values.indexOf(identifier) == -1) {
					values += identifier;
//					System.out.println("values: " + values);
				}				
			}
		}
		if(values == "") {
			values = "NONE";
		}else {
			char[] chars = values.toCharArray();
			Arrays.sort(chars);
			values = new String(chars);
		}
		return values;
	}
	
	public static String Reversal(String input) {
		String reversed = "";
		for (int i = input.length() - 1; i >= 0; i--){
			reversed += input.charAt(i);
		}
		return reversed;
	}
	
	//Finds common string going Left to Right
	public static String LR(String vala, String valb, String common){
		for(int a = 0; a < vala.length(); a++){
			if(valb.indexOf(vala.substring(a, a+1)) != -1){
				int loc = valb.indexOf(vala.substring(a, a+1));
//				System.out.println("value: " + valb.charAt(loc));
				common += valb.charAt(loc);
				valb = valb.substring(loc+1);
//				System.out.println("valb: "+ valb);
//				System.out.println("common: " + common);
			}
			
		}
		return common;
	}
	
	//Finds common string going Right to Left
	public static String RL(String vala, String valb, String common){
		vala = Reversal(vala);
		valb = Reversal(valb);
		for(int a = 0; a < vala.length(); a++){
			if(valb.indexOf(vala.substring(a, a+1)) != -1){
				int loc = valb.indexOf(vala.substring(a, a+1));
//				System.out.println("value: " + valb.charAt(loc));
				common += valb.charAt(loc);
				valb = valb.substring(loc+1);
//				System.out.println("valb: "+ valb);
//				System.out.println("common: " + common);
			}
			
		}
		return common;
	}
	
	//Defines the two input strings and runs parsing actions
	public static void execute(String input){
		inputA = input.substring(0, input.indexOf(' '));
//		System.out.println("inputA: " + inputA);
		inputB = input.substring(input.indexOf(' ')+1, input.length());
//		System.out.println("inputB: " + inputB);
		com1 = LR(inputA, inputB, ""); //A to B going Left to Right
//		System.out.println("com1: "+ com1);
		com2 = RL(inputA, inputB, ""); //A to B going Right to Left
//		System.out.println("com2: "+ com2);
		com3 = LR(inputB, inputA, ""); //B to A going Left to Right
//		System.out.println("com3: "+ com3);
		com4 = RL(inputB, inputA, ""); //B to A going Right to Left
//		System.out.println("com4: "+ com4);
		System.out.println("Final answer: " + common());
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String input;
		File file = new File("E://AP Compsci/ACSL_Diff_Intermediate/src/intermediateInput.txt");
		Scanner sc = new Scanner(file);
		for(int i = 0; i < 5; i++){
			input = sc.nextLine();
//			System.out.println(input);
			execute(input);
		}
		sc.close();
	}

}
