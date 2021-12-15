import java.io.*;
import java.util.*;

public class Stretch {
	public static int rows, columns, start, starting, endingDigit, max, moveMultiplier = 1;
	public static ArrayList<Integer> board = new ArrayList<Integer>();
	public static ArrayList<Integer> blocked = new ArrayList<Integer>();
	public static boolean goingRight;
	
	public static String flip(String input) {
		String flipped = "";
		for(int i = 0; i < input.length(); i++) {
			flipped += input.charAt(input.length()-i-1);
		}
		return flipped;
	}
	
	public static boolean traverseA(){
		boolean clean = true;
		int traverseStarting = starting;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < blocked.size(); j++){
				if(traverseStarting == blocked.get(j) || traverseStarting > max || traverseStarting < 1)
					clean = false;
				if((i == 0 || i == 1) && traverseStarting % columns == endingDigit)
					clean = false;
			}
			if(traverseStarting % columns != endingDigit)
				traverseStarting += moveMultiplier;
		}
		if(clean) {
			starting = traverseStarting;
		}
		return clean;
	}
	
	public static boolean traverseB(){
		boolean clean = true;
		int traverseStarting = starting;
		if(starting % columns == 1) {
			clean = false;
		}else {
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < blocked.size(); j++){
					if(traverseStarting == blocked.get(j) || traverseStarting > max || traverseStarting < 1){
						clean = false;
					}
					if((i == 0 || i == 1) && traverseStarting % columns == endingDigit)
						clean = false;
				}
				if(!goingRight){
					switch(i){
					case 0: traverseStarting += moveMultiplier;
						break;
					case 1: traverseStarting += moveMultiplier * columns;
						break;
					case 2: 
						if(traverseStarting % columns != endingDigit)
							traverseStarting += moveMultiplier;
						break;
					}				
				}else{
					switch(i){
					case 0: traverseStarting += moveMultiplier * columns;
						break;
					case 1: traverseStarting += moveMultiplier;
						break;
					case 2: 
						if(traverseStarting % columns != endingDigit)
							traverseStarting += moveMultiplier;
						break;
					}
				}
			}
		}
		if(clean){
			starting = traverseStarting;
		}
		return clean;
	}
	
	public static boolean traverseC(){
		boolean clean = true;
		int traverseStarting = starting;
		if(starting % columns == 0 || (starting + 1) % columns == 0){
			clean = false;
		}else{
			for(int i = 0; i < 4; i++){
				for(int j = 0; j < blocked.size(); j++){
					if(traverseStarting == blocked.get(j) || traverseStarting > max || traverseStarting < 1){
						clean = false;
					}
				}
				if(!goingRight){
					switch(i){
					case 0: traverseStarting += moveMultiplier*columns;
						break;
					case 1: traverseStarting += moveMultiplier*columns;
						break;
					case 2: traverseStarting += moveMultiplier;
						break;
					case 3: 
						if(traverseStarting % columns != endingDigit)
							traverseStarting += moveMultiplier;
						break;
					}
				}else{
					switch(i){
					case 0: traverseStarting += moveMultiplier;
						break;
					case 1: traverseStarting += moveMultiplier*columns;
						break;
					case 2: traverseStarting += moveMultiplier*columns;
						break;
					case 3: 
						if(traverseStarting % columns != endingDigit)
							traverseStarting += moveMultiplier;
						break;
					}
				}
			}
		}
		if(clean){
			starting = traverseStarting;
		}
		return clean;
	}
	
	
	public static String execute(){
		String output = "";
		max = rows * columns; // maximum integer value that can be on the board
//		System.out.println(blocked);
		//Setting direction based on start location
		if(start % columns == 0) {
			goingRight = false;
			endingDigit = 1;
		}
		else {
			goingRight = true;
			endingDigit = 0;
		}
//		System.out.println("direction is right: " + goingRight);
		//Set the multiplier for values based on traversal direction
		if(!goingRight)
			moveMultiplier = -1;
		else
			moveMultiplier = 1;
//		System.out.println("multiplier: " + moveMultiplier);
		//Traversal of the board and return characters if valid
		starting = start;
		int counter = 0;
		while(starting % columns != endingDigit) {
			switch(counter % 3){
			case 0: 
				if(traverseA()){
//					System.out.println("A true!");
					output += "A";
				}
				counter++;
				break;
			case 1:
				if(traverseB()){
//					System.out.println("B true!");
					output += "B";
				}
				counter++;
				break;
			case 2:
				if(traverseC()){
//					System.out.println("C true!");
					output += "C";
				}
				counter++;
				break;
			}
//			System.out.println("Iterations: " + counter);
		}
		//flips output string if right to left
		if(!goingRight) {
//			System.out.println("Flipped");
			output = flip(output);
		}
		return output;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file = new File("F://int3.txt");
		Scanner sc = new Scanner(file);
		for(int i = 0; i < 5; i++){
			rows = sc.nextInt();
//			System.out.println("rows: " + rows);
			columns = sc.nextInt();
//			System.out.println("columns: " + columns);
			start = sc.nextInt();
//			System.out.println("start: " + start);
			int numBlocked = sc.nextInt();
//			System.out.println("numblocked: " + numBlocked);
			blocked.clear();
			for(int j = 0; j < numBlocked; j++){
				blocked.add(sc.nextInt());
			}
			String output = execute();
			System.out.println(output);
//			System.out.println("\n-------Execution Successful-------\n");
		}
	}

}
