#Kavin M. Govindarajan
#Feb. 4, 2020
#Int5
#Enloe
#Potter
def remove_common(word1, word2):
    non_sim_w1 = ""
    non_sim_w2 = ""
    counter = 0
    for i in range(0,min(len(word1), len(word2))):
        if word1[i] != word2[i]:
            non_sim_w1 += word1[i]
            non_sim_w2 += word2[i]
        counter = i
    #filling in w1
    for i in range(counter + 1, len(word1)):
        non_sim_w1 += word1[i]
    for i in range(counter + 1, len(word2)):
        non_sim_w2 += word2[i]
    non_simmed = [non_sim_w1, non_sim_w2]
    return non_simmed

with open('F:/2inttest.txt') as f:
    for line in f:
        #print(line)
        #separating the file input into two words
        splitter = line.split()
        word1 = str(splitter[0])
        word2 = str(splitter[1])
        #print(word1)
        #print(word2)

        switched = False
        #Preliminary removal
        non_sim_w1 = remove_common(word1, word2)[0]
        non_sim_w2 = remove_common(word1, word2)[1]

        #print("removed: " + non_sim_w1)
        #print("removed: " + non_sim_w2)

        #logic
        iterator = 0
        while iterator < min(len(non_sim_w1), len(non_sim_w2)):
            #print(non_sim_w1 + " " + non_sim_w2)
            splitted = remove_common(non_sim_w1, non_sim_w2)
            non_sim_w1 = splitted[0]
            non_sim_w2 = splitted[1]
            #print(non_sim_w1)
            #print(non_sim_w2)
        
            #checking for adjustment
            #checking string 2 first
            #TODO: Fix edge case where it checks a char that doesn't exist
            #ex... DE/GBP, where its at B and tries to check for extra, but that extra doesn't exist
            if (iterator  < len(non_sim_w2)-1) and (non_sim_w2[iterator+1] == non_sim_w1[iterator]):
                #print("w2_extension")
                non_sim_w2 = non_sim_w2.replace(str(non_sim_w2[iterator]), "", 1)
                iterator = 0
            elif (iterator < len(non_sim_w1) - 1) and (non_sim_w1[iterator+1] == non_sim_w2[iterator]): 
                #print("w1_extension")
                non_sim_w1 = non_sim_w1.replace(str(non_sim_w1[iterator]), "", 1)
                iterator = 0
            else:
                iterator += 1
        #print("Post_logic_w1: " + non_sim_w1)
        #print("Post_logic_w2: " + non_sim_w2)
        sum = 0
        for i in range(0, min(len(non_sim_w1), len(non_sim_w2))):
            #print(non_sim_w2[i] + " to ascii: " + str(ord(non_sim_w2[i])))
            #print(non_sim_w1[i] + " to ascii: " + str(ord(non_sim_w1[i])))
            #print(ord(non_sim_w2[i]) - ord(non_sim_w1[i]))
            sum += -(ord(non_sim_w2[i]) - ord(non_sim_w1[i]))
        #print(sum)
        sum += max(len(non_sim_w1), len(non_sim_w2)) - min(len(non_sim_w1),len(non_sim_w2))
        print(sum)

