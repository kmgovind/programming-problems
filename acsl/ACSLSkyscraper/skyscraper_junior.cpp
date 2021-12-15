/*  ACSL Skyscraper Junior
    Kavin Muthukumaran Govindarajan
    William G. Enloe High School
*/
#include <iostream>
#include <algorithm>
#include <string>
#include <stdlib.h>
using namespace std;

void check_left(int property[] , int *left_view){
  int tallest = 0;
  for(int position = 0; position < 5; position++){
    if(property[position] > tallest){
      tallest = property[position];
      *left_view +=1;
    }
  }
}
void check_right(int property[] , int *right_view){
  int tallest = 0;
  for(int position = 4; position >= 0; position--){
    if(property[position] > tallest){
      tallest = property[position];
      *right_view +=1;
    }
  }
}
int main(){
  int counter, left_amount = 0, right_amount = 0, left_view = 0, right_view = 0;
  int property[5] = {0,0,0,0,0};
  string s = "12345";
  string temp;
for(int run = 0; run < 5; run++){
  counter = 0, left_view = 0, right_view = 0;
  cout << "Enter left:" << endl;
  cin >> left_amount;
  cout << "Enter right:" << endl;
  cin >> right_amount;
  sort(s.begin(), s.end());
  do{
        left_view = 0, right_view = 0;
    for(int change = 0; change < 5; change++){
        temp = s.substr(change,1);
        property[change] = atoi(temp.c_str());
    }
    check_left(property, &left_view);
    check_right(property, &right_view);
    if(left_amount == left_view){
        if(right_amount == right_view){
            counter +=1;
        }
    }
  }while(next_permutation(s.begin(), s.end()));
    cout << "The number of different arrangements are:" << endl;
    cout << counter << "\n"<< endl;
}
return 0;
}
