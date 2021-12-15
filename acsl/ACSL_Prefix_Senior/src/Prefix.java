import java.io.*;
import java.util.*;

public class Prefix {
	public static String input;
	public static ArrayList<String> exp = new ArrayList<String>();
	public static String[] operators = {"+","-","*","@" ,">", "|"};

	public static String evaluate(ArrayList<String> cutout){
		int value = 0;
		int get1 = Integer.decode(cutout.get(1));
		switch(cutout.get(0)){
		case "+": value = get1 + Integer.decode(cutout.get(2));
			break;
		case "-": value = get1 - Integer.decode(cutout.get(2));
			break;
		case "*": value = get1 * Integer.decode(cutout.get(2));
			break;
		case "@": 
			if(get1 > 0){
				value = Integer.decode(cutout.get(2));
			}else{
				value = Integer.decode(cutout.get(3));
			}
			break;
		case ">":	value = Math.max(get1, Math.max(Integer.decode(cutout.get(2)), Integer.decode(cutout.get(3))));
			break;
		case "|": value = Math.abs(get1);
			break;
		}
		return Integer.toString(value);
	}
	
	public static String simplify(ArrayList<String> expression) throws InterruptedException{
		int j = expression.size() - 1, k = 0;
		boolean operatorFound = false;
		ArrayList<String> cutout = new ArrayList<String>();
//		System.out.println("Expression 1: " + expression);
		cutout.clear();
		while(!operatorFound && j >= 0) {
			while(!operatorFound && k < operators.length) {
//				System.out.println("J: " + j + " K: " + k);
				if((expression.get(j)).equals(operators[k])) {
					operatorFound = true;
//					System.out.println("found");
					if(k == 0 || k == 1 || k ==2) {
						for (int i = 0; i < 3; i++) {
							cutout.add(expression.get(j+i));
						}
						for(int i = 0; i < 3; i++) {
							expression.remove(j);
						}
					}else if(k == 3 || k == 4) {
						for(int i = 0; i < 4; i++) {
							cutout.add(expression.get(j+i));
						}
						for(int i = 0; i < 4; i++) {
							expression.remove(j);
						}
					}else {
						for (int i = 0; i < 2; i++) {
							cutout.add(expression.get(j+i));
						}
						for(int i = 0; i < 2; i++) {
							expression.remove(j);
						}
					}
					expression.add(j, evaluate(cutout));
				}else {
					k++;
				}		
			}
			j--;
			k = 0;
		}
//		System.out.println("Cutout: " + cutout);
//		System.out.println("Expression 2: " + expression);
//		Thread.sleep(1000);
		if(expression.size() > 1) {
			simplify(expression);
		}
		return expression.get(0);
	}
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		File file = new File("F://sr4.txt");
		Scanner sc = new Scanner(file);
		for(int i = 0; i < 5; i++){
			exp.clear();
			input = sc.nextLine();
//			System.out.println(input);
			while(input.contains(" ")){
				exp.add(input.substring(0, input.indexOf(" ")));
				input = input.substring(input.indexOf(" ") + 1);
//				System.out.println("Input: " + input);
			}
			exp.add(input);		
//			for(int j = 0; j < exp.size(); j++) {
//				if((exp.get(j)).equals(" ")) {
//					exp.remove(j);
//				}
//			}
//			System.out.println(exp);
			System.out.println(simplify(exp));
//			System.out.println("------------------------------------------");
		}
		sc.close();
	}

}
