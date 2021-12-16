def bin2int(value):
    size = len(value)
    output = 0

    for index,element in enumerate(value):
        output += element*2**(size-index-1)
    
    return output

with open("input.txt") as inputFile:
    # read inputs
    inputs = list(map(lambda line : line.strip(), inputFile.readlines()))

    # initialize variables
    row = len(inputs) # number of rows in input
    col = len(inputs[0]) # number of digits in each row
    gamma = []
    epsilon = []

    # count gammas/epsilons
    for i in range(col):
        numOnes = 0
        for j in range(row):
            if inputs[j][i] == '1':
                numOnes += 1
        gamma.append(0 + (numOnes > row/2))
        epsilon.append(1 - (numOnes > row/2))

    print(bin2int(gamma)*bin2int(epsilon))