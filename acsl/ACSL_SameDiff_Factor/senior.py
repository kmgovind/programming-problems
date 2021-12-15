#Kavin M. Govindarajan
#Feb 4, 2020
#Sr5
#Enloe
#Potter
import re
longest_common = []

def longest_same(string1, string2):
    ls = []
    longest_ls = []
    l1 = [string1[i: j] for i in range(len(string1)) 
          for j in range(i + 1, len(string1) + 1)]
   # print("l1: ", l1)
    
    l2 = [string2[i: j] for i in range(len(string2)) 
          for j in range(i + 1, len(string2) + 1)]
    #print("l2: ", l2)
    for substr in l1:
        for substr2 in l2:
            if (substr == substr2):
                ls.append(substr)
    ls = sorted(ls)
   # print("ordered ls: ", ls)
    if(len(ls) != 0):
        longest_length = 0
        for i in ls:
            if len(i) > longest_length:
                longest_length = len(i)
        for i in ls:
            if len(i) == longest_length:
                longest_ls.append(i)
        longest_ls = sorted(longest_ls)
        return(longest_ls[0])
    else:
        return("")
    
                

def splitter(string1, string2):
   # print("splitter1: ", string1)
   # print("splitter2: ", string2)
    common = longest_same(string1, string2)
    if(len(common) != 0):
       # print("common", common)
        longest_common.append(common)
        splitter(string1[0:string1.find(common)], string2[0:string2.find(common)])
        splitter(string1[string1.find(common) + len(common):], string2[string2.find(common) + len(common):])



with open('F:/2srtest.txt') as f:
    regex = re.compile('[^a-zA-Z]')
    for input in range(0,5):
        longest_common = []
        sent1 = f.readline()
        sent2 = f.readline()
        sent1 = regex.sub('', sent1)
        sent2 = regex.sub('', sent2)
        sent1 = sent1.upper()
        sent2 = sent2.upper()
        #print(sent1)
       # print(sent2)
        splitter(sent1, sent2)
        #print(longest_common)
        #print(longest_common)
        sum = 0
        for subber in longest_common:
            sum += len(subber)
        #print(sent1)
        #print(sent2)
        print(sum)
