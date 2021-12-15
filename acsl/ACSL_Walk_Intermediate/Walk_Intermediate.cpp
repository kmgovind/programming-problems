//Kavin Govindarajan
//Lori Kubik
//Intermediate 2B
 #include <bits/stdc++.h>
using namespace std;

struct position{
  int numberHit = 0;//checks the times of entry, defaults at 0 showing that has not been entered before
  char value = 0;//this should either be 1 or 0
  char direction;//this shows which side of the box the player enters from (L,R,A,B)
  int row = 0;//row on the grid will be from 1-8 due to acsl array
  int col = 0;//column on the grid will be from 1-8 due to acsl array
};

string hexToBinary(string input){
  string output;
  if(input.length() == 1){output = "0000";}
    for(int i = 0; i < 2; i++){
      switch(input[i]){
        case '1': output.append("0001");break;
        case '2': output.append("0010");break;
        case '3': output.append("0011");break;
        case '4': output.append("0100");break;
        case '5': output.append("0101");break;
        case '6': output.append("0110");break;
        case '7': output.append("0111");break;
        case '8': output.append("1000");break;
        case '9': output.append("1001");break;
        case 'A': output.append("1010");break;
        case 'B': output.append("1011");break;
        case 'C': output.append("1100");break;
        case 'D': output.append("1101");break;
        case 'E': output.append("1110");break;
        case 'F': output.append("1111");break;
      }
    }
  return output;
}

int main(){
   position defaultArray[8][8];
   position manipulatedArray[8][8];
   position walker;
   string rowValues;
   string input;
   ifstream myfile("intsample.txt"); //S://Public/CS/ACSLSample/3/intsample.txt
   if(myfile.is_open()){
        int rowNumber = 0;
        while(rowNumber < 8){
            if(rowNumber != 7){
                getline(myfile,input,' ');
            }else{getline(myfile,input);}
//            cout << input << "\t";
            rowValues = hexToBinary(input);
//            cout << rowValues << endl;
            for(int i = 0; i < 8; i++){
                defaultArray[rowNumber][i].value = rowValues[i];
            }
            rowNumber++;
        }
//        for(int i = 0; i < 8; i++){
//            for (int j = 0; j < 8; j++){
//                cout << defaultArray[i][j].value << "\t";
//            }
//            cout << endl;
//        }
        for(int i = 0; i < 5; i++){//running for number of inputs
            for(int row = 0; row < 8; row++){
                for(int col = 0; col < 8; col++){
                    defaultArray[row][col].numberHit = 0; //resetting entry time values
                }
            }
         getline(myfile,input);
         cout << input << endl;
         char temp = input[0];
         walker.row = temp - '0';
//         cout << "row: " << walker.row << endl;
         temp = input[2];
         walker.col = temp - '0';
//         cout << "col: " << walker.col << endl;
         walker.direction = input[4];
//         cout << "dir: " << walker.direction << endl;
         int numOfMoves = input[6] - '0';
//         cout << "Num: " << numOfMoves << endl;
         for(int turn = 0; turn < numOfMoves; turn++){
//                cout << "dir: " << walker.direction  << endl;
//                cout << "row: " << walker.row << endl;
//                cout << "col: " << walker.col << endl;
            if(defaultArray[(walker.row)-1][(walker.col)-1].value == '0'){//case for when the location is a 0
                switch(walker.direction){//affecting motion direction based on char direction
                    case 'L':if(walker.col == 8){walker.col = 1;}
                        else{walker.col += 1;}
                        break;
                    case 'R': if(walker.col == 1){walker.col = 8;}
                        else{walker.col -= 1;}
                        break;
                    case 'A': if(walker.row == 8){walker.row = 1;}
                        else{walker.row += 1;}
                        break;
                    case 'B': if(walker.row == 1){walker.row = 8;}
                        else{walker.row -= 1;}
                        break;
                }
            }else{
                defaultArray[walker.row-1][walker.col-1].numberHit += 1;
                switch(walker.direction){
                case 'L':
                    switch((defaultArray[(walker.row)-1][(walker.col)-1].numberHit - 1) % 4){//using mod to check which angle
                        case 0:walker.direction = 'B';
                            if(walker.row == 1){walker.row = 8;}
                            else{walker.row -= 1;}
                            break;
                        case 1:walker.direction = 'L';
                            if(walker.col == 8){walker.col = 1;}
                            else{walker.col += 1;}
                            break;
                        case 2:walker.direction = 'A';
                            if(walker.row == 8){walker.row = 1;}
                            else{walker.row += 1;}
                            break;
                        case 3: walker.direction = 'R';
                            if(walker.col == 1){walker.col = 8;}
                            else{walker.col -= 1;}
                            break;
                    }
                    break;
                case 'R':
                    switch((defaultArray[(walker.row)-1][(walker.col)-1].numberHit - 1) % 4){
                        case 0:walker.direction = 'A';
                            if(walker.row == 8){walker.row = 1;}
                            else{walker.row += 1;}
                            break;
                        case 1:walker.direction = 'R';
                            if(walker.col == 1){walker.col = 8;}
                            else{walker.col -= 1;}
                            break;
                        case 2:walker.direction = 'B';
                            if(walker.row == 1){walker.row = 8;}
                            else{walker.row -= 1;}
                            break;
                        case 3: walker.direction = 'L';
                            if(walker.col == 8){walker.col = 1;}
                            else{walker.col += 1;}
                            break;
                    }
                    break;
                case 'A':
                    switch((defaultArray[(walker.row)-1][(walker.col)-1].numberHit - 1) % 4){
                        case 0:walker.direction = 'L';
                            if(walker.col == 8){walker.col = 1;}
                            else{walker.col += 1;}
                            break;
                        case 1:walker.direction = 'A';
                            if(walker.row == 8){walker.row = 1;}
                            else{walker.row += 1;}
                            break;
                        case 2:walker.direction = 'R';
                            if(walker.col == 1){walker.col = 8;}
                            else{walker.col -= 1;}
                            break;
                        case 3: walker.direction = 'B';
                            if(walker.row == 1){walker.row = 8;}
                            else{walker.row -= 1;}
                            break;
                    }
                    break;
                case 'B':
                    switch((defaultArray[(walker.row)-1][(walker.col)-1].numberHit - 1) % 4){
                        case 0:walker.direction = 'R';
                            if(walker.col == 1){walker.col = 8;}
                            else{walker.col -= 1;}
                            break;
                        case 1:walker.direction = 'B';
                            if(walker.row == 1){walker.row = 8;}
                            else{walker.row -= 1;}
                            break;
                        case 2:walker.direction = 'L';
                            if(walker.col == 8){walker.col = 1;}
                            else{walker.col += 1;}
                            break;
                        case 3: walker.direction = 'A';
                            if(walker.row == 8){walker.row = 1;}
                            else{walker.row += 1;}
                            break;
                    }
                    break;
                }
            }
         }
         cout << walker.row  << ", " << walker.col << endl;
        }
    }
  return 0;
}
