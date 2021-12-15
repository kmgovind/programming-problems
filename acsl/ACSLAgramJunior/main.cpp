//Kavin M. Govindarajan
//4B
//12/1/2016
#include <iostream>
#include<stdlib.h>
using namespace std;

int main()
{
    system("color 0a");
    for(int i = 0; i<5; i++)
    {
        int leading_value, hand_value1, hand_value2, hand_value3, hand_value4, hand_value5;
        char leading_char, hand_char1, hand_char2, hand_char3, hand_char4, hand_char5;
        int check[10];
        cout<<"Enter leading card value:";
        cin>> leading_value;
        cout<<"Enter leading card suit in caps:";
        cin>> leading_char;
        cout<<"Enter your hand 1 card value:";
        cin>> hand_value1;
        cout<<"Enter your hand 1 card suit:";
        cin>> hand_char1;
        cout<<"Enter your hand 2 card value:";
        cin>> hand_value2;
        cout<<"Enter your hand 2 card suit:";
        cin>> hand_char2;
        cout<<"Enter your hand 3 card value:";
        cin>> hand_value3;
        cout<<"Enter your hand 3 card suit:";
        cin>> hand_char3;
        cout<<"Enter your hand 4 card value:";
        cin>> hand_value4;
        cout<<"Enter your hand 4 card suit:";
        cin>> hand_char4;
        cout<<"Enter your hand 5 card value:";
        cin>> hand_value5;
        cout<<"Enter your hand 5 card suit:";
        cin>> hand_char5;
        if(leading_char != hand_char1 && leading_char != hand_char2 && leading_char != hand_char3 && leading_char != hand_char4 && leading_char != hand_char5)
        {
            cout<< "NONE" << endl;
        }
        else
        {
            int k = 0;
            if(leading_char == hand_char1)
            {
                check[k] = hand_value1;
                k++;
            }
            if(leading_char == hand_char2)
            {
                check[k] = hand_value2;
                k++;
            }
            if(leading_char == hand_char3)
            {
                check[k] = hand_value3;
                k++;
            }
            if(leading_char == hand_char4)
            {
                check[k] = hand_value4;
                k++;
            }
            if(leading_char == hand_char5)
            {
                check[k] = hand_value5;
                k++;
            }
            int least=10, great=0;
            for (int l=0; l < k; l++)
            {
                if(leading_value < check[l])
                {
                    if(great == 0 || great > check[l])
                    {
                        great = check[l];
                    }
                }
                else
                {
                    if( least > check[l] || least == 10)
                    {
                        least = check[l];
                    }
                }
            }
            if(leading_value < great)
            {
                cout << great << " "<< leading_char << endl;
            }
            else
            {
                cout<< least << " "<< leading_char << endl;
            }
        }
    }
    system("pause");
    return 0;
}
