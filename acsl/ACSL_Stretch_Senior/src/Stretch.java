import java.io.*;
import java.util.*;

public class Stretch {
	public static int rows, columns, start, starting, endingDigit, max, moveMultiplier = 1, counter = 0;
	public static ArrayList<Integer> blocked = new ArrayList<Integer>();
	public static boolean goingRight, bUpwards;
	
	public static boolean traverseA(){
		boolean clean = true;
		int traverseStarting = starting;
		for(int i = 0; i < 3; i++){
//			System.out.println("Starting: " + traverseStarting);
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
	
	public static boolean traverseB(char previousPiece){
		boolean clean = true;
		boolean worked = true;
		bUpwards = false;
		int traverseStarting = starting;
		if(starting % columns == 1 || starting % columns == 0) {
			clean = false;
		}
		else if(!goingRight && previousPiece == 'C') {
			for(int i = 0; i < 3; i++) {
//				System.out.println("starting: " + traverseStarting);
				for(int j = 0; j < blocked.size(); j++) {
					if(traverseStarting == blocked.get(j) || traverseStarting > max || traverseStarting < 1) {
						clean = false;
					}
				}
				switch(i) {
				case 0: traverseStarting += -1 * columns;
					break;
				case 1: traverseStarting += -1 * columns;
					break;
				case 2: 
					if(traverseStarting % columns != endingDigit)
						traverseStarting += moveMultiplier;
					break;
				}
			}
			if(clean)
				bUpwards = true;
		}else if(goingRight && previousPiece == 'D') {
			for(int i = 0; i < 3; i++) {
//				System.out.println("Starting: " + traverseStarting);
				for(int j = 0; j < blocked.size(); j++) {
					if(traverseStarting == blocked.get(j) || traverseStarting > max || traverseStarting < 1) {
						clean = false;
					}
				}
				switch(i) {
				case 0: traverseStarting += columns;
					break;
				case 1: traverseStarting += columns;
					break;
				case 2: 
					if(traverseStarting % columns != endingDigit)
						traverseStarting += moveMultiplier;
					break;
				}
			}
			if(clean)
				bUpwards = false;
		}else {
			//Check upwards
			for(int i = 0; i < 3; i++) {
//				System.out.println("TraverseStarting: " + traverseStarting);
				for(int j = 0; j < blocked.size(); j++) {
					if(traverseStarting == blocked.get(j) || traverseStarting > max || traverseStarting < 1) {
						worked = false;
					}
				}
				switch(i) {
				case 0: traverseStarting += -1 * columns;
					break;
				case 1: traverseStarting += -1 * columns;
					break;
				case 2:
					if(!worked) {
						traverseStarting = starting;
						worked = true;
						for(int k = 0; k < 3; k++) {
//							System.out.println("traversestarting: " + traverseStarting);
							for(int j = 0; j < blocked.size(); j++) {
								if(traverseStarting == blocked.get(j) || traverseStarting > max || traverseStarting < 1) {
									worked = false;
								}
							}
							if(k == 0 || k == 1)
								traverseStarting += columns;
						}
						if(worked)
							bUpwards = false;
					}
					else if(!bUpwards)
						bUpwards = true;
					if(worked) {
						traverseStarting += moveMultiplier;
					}else {
						clean = false;
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
		if(starting % columns == 1) {
			clean = false;
		}else if(bUpwards && goingRight) {
			clean = false;
		}
		else {
			for(int i = 0; i < 3; i++){
//				System.out.println("traverseStarting: " + traverseStarting);
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
	
	public static boolean traverseD(char previous) {
		boolean clean = true;
		int traverseStarting = starting;
		if(starting % columns == 0 || (starting + 1) % columns == 0){
			clean = false;
		}
		else if(previous == 'B' && !goingRight) {
			clean = false;
		}
		else{
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
	
	public static boolean traverseE() {
		boolean clean = true;
		int traverseStarting = starting;
		for(int i = 0; i < 4; i++){
//			System.out.println("TraverseStarting: " + traverseStarting);
			for(int j = 0; j < blocked.size(); j++){
//				System.out.println("J: " + j);
				if(traverseStarting == blocked.get(j) || traverseStarting > max || traverseStarting < 1){
//					System.out.println("BLOCKED");
					clean = false;
				}
				if(i != 3 && traverseStarting % columns == endingDigit) {
					clean = false;
				}
			}
			switch(i){
			case 0: traverseStarting += moveMultiplier;
				break;
			case 1: traverseStarting += moveMultiplier * columns;
				break;
			case 2: traverseStarting += moveMultiplier;
				break;
			case 3:
				if(traverseStarting % columns != endingDigit)
					traverseStarting += moveMultiplier;
				break;
			}				
		}
		if(clean){
			starting = traverseStarting;
		}
		return clean;
	}
	
	public static String execute() throws InterruptedException{
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
		counter = 0;
		while(starting % columns != endingDigit) {
			switch(counter % 5){
			case 0: 
//				System.out.println("enter A!");
				if(traverseA()){
//					System.out.println("A true!");
					output += "A";
				}
				counter++;
				break;
			case 1:
//				System.out.println("enter B!");
				if(output == "") {
					if(traverseB(' ')) {
//						System.out.println("B true!");
						output += "B";
					}
				}else {
					if(traverseB(output.charAt(output.length()-1))){
//						System.out.println("B true!");
						output += "B";
					}
				}
				counter++;
				break;
			case 2:
//				System.out.println("enter C!");
				if(traverseC()){
//					System.out.println("C true!");
					output += "C";
				}
				counter++;
				break;
			case 3:
				if(output == "") {
					if(traverseD(' ')) {
//						System.out.println("D true!");
						output += "D";
					}
				}else {
					if(traverseD(output.charAt(output.length()-1))){
//						System.out.println("D true!");
						output += "D";
					}
				}
				counter++;
				break;
			case 4:
//				System.out.println("enter E!");
				if(traverseE()) {
//					System.out.println("E true!");
					output += "E";
				}
				counter++;
				break;
			}
//			System.out.println("Iterations: " + counter);
//			Thread.sleep(2000);
		}
		return output;
	}

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		File file = new File("S://Public/CS/sr3.txt");
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
		sc.close();
	}

}
