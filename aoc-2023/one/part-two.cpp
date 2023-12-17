#include <algorithm>
#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <locale.h>

int string_parse(std::string filestring){
    // Replace text-based numbers with numerical values
    // Replace one with 1

    std::vector<std::string> searchstring = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    std::vector<std::string> replacestring = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    int counter = 0;
    std::string substring = "";
    while(counter < filestring.length()){
        counter += 1;
        substring = filestring.substr(0, counter);
        std::cout << substring << std::endl;
        std::cout << filestring << std::endl;
        for(int i = 0; i < searchstring.size(); i++){
            if(substring.find(searchstring.at(i)) != std::string::npos){
                filestring.replace(filestring.find(searchstring.at(i)), searchstring.at(i).length(), replacestring.at(i));
                counter = 0;
            }
        }
    }
    
    filestring.erase(std::remove_if(filestring.begin(), filestring.end(), (int(*)(int))std::isalpha), filestring.end()); // Erase non-numerical values
    std::string output;
    output += filestring[0]; // Append first integer to string
    output += filestring.back(); // Append last integer to string
    return std::stoi(output);
}

int file_parse(std::string filename){
    unsigned int sum = 0;
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

int main(){
    // std::string infile_name = "two_sample_input.txt";
    std::string infile_name = "input.txt";
    std::string outfile_name = "parttwo-output.txt";
    std::ofstream outfile;
    outfile.open(outfile_name);
    outfile << file_parse(infile_name);
    outfile.close();
    return 0;
}