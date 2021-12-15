//Kavin Govindarajan
//04-12-2018
//Mrs.Kubik
//Enloe Magnet HS
//Contest 4 INT5
#include <bits/stdc++.h>
using namespace std;

struct DATA{
    char letters;
    int counts;
};

void quicksort(DATA values[], int left, int right){
    int i = left, j = right;
    DATA temp;
    int temporary;
    char pivot = values[(left+right)/2].letters;
    while(i<=j){
        while(values[i].letters < pivot){i++;}
        while(values[j].letters > pivot){j--;}
        if(i <= j){
            temp = values[i];
            values[i] = values[j];
            values[j] = temp;
//            temporary = values[i].counts;
//            values[i].counts = values[j].letters;
//            values[j].letters = temporary;
            i++;
            j--;
        }
    }
    if( left < j)
        quicksort(values, left, j);
    if(i < right)
        quicksort(values, i, right);
}

void removeFrom(DATA values[], string output[], string manipString){
    for(int i = 0; i < manipString.length(); i++){
        bool exists = 0;
        int pos = 0, j = 0;
        while(j < 26){
            if(values[j].letters == manipString[i]){
                pos = j;
                exists = 1;
                j = 26;
            }
            if(!exists){j++;}
        }
        if(exists){values[pos].counts--;}
        if(values[pos].counts == 0){
            values[pos].letters = '~';
        }
        quicksort(values,0,25);
//        cout << "MEM: ";
//        for(int j = 0; j < 26; j++){
//            cout << values[j].letters << ", ";
//        }
//        cout << endl;
//        cout << "COUNT: ";
//        for(int j = 0; j < 26; j++){
//            cout << values[j].counts << ", ";
//        }
//        cout << endl;
        for(int j = 0; j < 26; j++){
            char temp = values[j].letters;
            string temporary = output[j];
            output[j] = temp + temporary;
//            cout << output[j] << ", ";
        }
//        cout << endl;
    }


}

void populate(DATA values[], string output[], string manipString){
    for(int i = 0; i < manipString.length(); i++){
        bool exists = 0;
        int pos = 0, j = 0;
        while(j < 26){
            if(values[j].letters == manipString[i]){
                pos = j;
                exists = 1;
                j = 26;
            }
            if(!exists){j++;}
        }
        if(exists){values[pos].counts++;}
        else{
//                cout << "test" << endl;
            values[25].letters = manipString[i];
            values[25].counts = 1;
        }
        quicksort(values,0,25);
//        cout << "MEM: ";
//        for(int j = 0; j < 26; j++){
//            cout << values[j].letters << ", ";
//        }
//        cout << endl;
//        cout << "COUNT: ";
//        for(int j = 0; j < 26; j++){
//            cout << values[j].counts << ", ";
//        }
//        cout << endl;
        for(int j = 0; j < 26; j++){
            char temp = values[j].letters;
            string temporary = output[j];
            output[j] = temp + temporary;
//            cout << output[j] << ", ";
        }
//        cout << endl;
    }

}

int main()
{
//    vector<DATA> values;
    DATA values[26];
    string command, output[26], input;
    ifstream myfile("S://Public/CS/check/int.txt");//duplicates.txt
    if(myfile.is_open()){
        while(myfile.good()){
//            system("pause");
            getline(myfile, command);
//            cout << "input: " << command << endl;
            int commandEnd = command.find(' ');
            input = command.substr(commandEnd, command.length());
            command = command.substr(0, commandEnd);
//            cout << "command: " << command << endl;
//            cout << "params: " << input << endl;
                while(input.find(' ') != -1){
                input.erase(input.find(' '), 1);
            }
            for(int i = 0; i < input.length(); i++){
                if(isalpha(input[i])){
                    input[i] = toupper(input[i]);
                }
            }
//            cout << "capitalized params: " << input << endl;
            if(command == "RESET"){
                for(int i = 0; i < 26; i++){
                    output[i] = "0";
                    values[i].counts = 0;
                    values[i].letters = '~';
                }
                populate(values,output,input);
            }else if(command == "ADD"){
                populate(values,output,input);
            }else if(command == "DELETE"){
                removeFrom(values,output,input);
            }else if(command == "REPORT"){
                int position = atoi(input.c_str()) - 1;
//                cout << "position: " << position << endl;
                string alphaOutput;
                string outputTemp = output[position];
                for(int i = outputTemp.length(); i >= 0; i--){
                    if(isalpha(outputTemp[i])){
                        alphaOutput += outputTemp[i];
                    }
                }
                int i= 0;
                while(i < alphaOutput.length()){
                    if(alphaOutput[i] == alphaOutput[i+1]){
                        alphaOutput.erase(i+1, 1);
                    }else{i++;}
                }
                cout << alphaOutput << endl;
            }
        }
    }
    return 0;
}
