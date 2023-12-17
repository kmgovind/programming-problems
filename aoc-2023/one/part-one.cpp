#include <algorithm>
#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <locale.h>

int string_parse(std::string filestring){
    filestring.erase(std::remove_if(filestring.begin(), filestring.end(), (int(*)(int))std::isalpha), filestring.end()); // Erase non-numerical values
    std::string output;
    output += filestring[0]; // Append first integer to string
    output += filestring.back(); // Append last integer to string
    return std::stoi(output);
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

int main(){
    // std::string infile_name = "sample_input.txt";
    std::string infile_name = "input.txt";
    std::string outfile_name = "partone-output.txt";
    std::ofstream outfile;
    outfile.open(outfile_name);
    outfile << file_parse(infile_name);
    outfile.close();
    return 0;
}