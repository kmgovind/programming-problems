#include <algorithm>
#include <iostream>
#include <string>
#include <fstream>
#include <vector>


int string_parse(std::string filestring){
	// Erase whitespace from string
	filestring.erase(std::remove_if(filestring.begin(), filestring.end(), (int(*)(int))std::isspace), filestring.end());

	// Erase game number
	int pos = filestring.find(":");
	filestring.erase(0, pos+1);

	std::string substring = "";
	std::string getcolor = "";
	int subpos = 0;
	int tempval = 0;
	int red = 0;
	int blue = 0;
	int green = 0;

	// Get each subgame (";" separated)
	while(!filestring.empty()){
		if(filestring.find(";") != std::string::npos){
			pos = filestring.find(";");
		}else{
			pos = filestring.length();
		}
		substring = filestring.substr(0, pos);
		
		// For each subgame check if cubes are valid
		while(!substring.empty()){
			if(substring.find(",") != std::string::npos){
				subpos = substring.find(",");	
			}else{
				subpos = substring.length();
			}
			getcolor = substring.substr(0,subpos);
			std::string numval = "";
			for(int i = 0; i < getcolor.length(); i++){
				if(isdigit(getcolor[i])) numval += getcolor[i];
			}
			tempval = std::stoi(numval);
			getcolor.erase(std::remove_if(getcolor.begin(), getcolor.end(), (int(*)(int))std::isdigit), getcolor.end());

			if(getcolor[0] == 'r'){
				red = std::max(red, tempval);
			}else if(getcolor[0] == 'b'){
				blue = std::max(blue, tempval);
			}else if(getcolor[0] == 'g'){
				green = std::max(green, tempval);
			}

			substring.erase(0, subpos+1);
		}

		// Erase subgame from filestring
		filestring.erase(0, pos+1);
	}

	return red*green*blue;
}

int file_parse(std::string filename){
	int sum = 0;
	std::ifstream infile;
	std::string filestring;

	infile.open(filename); // Open File

	// Loop through lines of file
	if(infile.is_open()){
		while(std::getline(infile, filestring)){
			sum += string_parse(filestring);
		}
	}else{
		std::cout << "Couldn't open file\n";
	}

	infile.close(); // Close File   
	return sum;
}

int main(int argc, char* argv[]){
	std::string infile_name = argv[1];
	std::string outfile_name = "output-two.txt";
	std::ofstream outfile;
	outfile.open(outfile_name);
	outfile << file_parse(infile_name);
	outfile.close();
	return 0;
}
