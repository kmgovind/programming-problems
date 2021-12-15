//Kavin M. Govindarajan
//1/30/19
//Enloe
//Senior - 5
//Contest #2


import java.io.*;
import java.util.*;


public class Diff {
	public static String com;
	public static String diff1;
	public static String diff2;
		
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
		if(common == "")
			common = "NONE";
		return common;
	}
	
	public static String comSentence(String a, String b) {
		String common = "";
		String temp;
		int loc;
		while(a.indexOf(" ") != -1 || a != "") {
//			System.out.println("A: " + a);
			if(a.indexOf(" ") != -1) {
				loc = a.indexOf(" ");
				temp = a.substring(0,loc);
				a = a.substring(a.indexOf(" ") + 1);
			}else {
				temp = a;
				a = "";
			}
//			System.out.println("temp: " + temp);
			if(b.indexOf(temp) != -1) {
				common += temp;
				loc = b.indexOf(temp);
				b = b.substring(0,loc) + b.substring(loc + temp.length());
//				System.out.println("B: " + b);
//				System.out.println("Common: " + common);
			}
			
		}
		return common;
	}
	
	//Defines the two input strings and runs parsing actions
	public static void execute(String inputA, String inputB){
		diff1 = comSentence(inputA, inputB);
//		System.out.println("diff1: " + diff1);
		diff2 = comSentence(inputB, inputA);
//		System.out.println("diff2: " + diff2);
		com = LR(diff1, diff2, "");
		System.out.println("Final Answer: " + com);
		
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String inputA, inputB;
		File file = new File("F://sr2.txt");
		Scanner sc = new Scanner(file);
		for(int i = 0; i < 5; i++){
			inputA = sc.nextLine();
			inputB = sc.nextLine();
//			System.out.println(inputA);
//			System.out.println(inputB);	
			execute(inputA, inputB);
			if(sc.hasNext()) {
				sc.nextLine();
//				System.out.println("spacer: " + spacer);
			}
		}
		sc.close();
	}

}
