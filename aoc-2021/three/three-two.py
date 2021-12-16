def bin2int(value):
    size = len(value)
    output = 0

    for index,element in enumerate(value):
        output += element*2**(size-index-1)
    
    return output

def oxyGenerator(values, iteration):
    row = len(values)

    if row == 1:
        return list(map(int,values[0]))

    numOnes = 0
    for i in range(row):
        if values[i][iteration] == '1':
            numOnes += 1
    
    if numOnes >= row/2:
        return oxyGenerator(list(filter(lambda vals: vals[iteration] == '1', values)), iteration + 1)
    else:
        return oxyGenerator(list(filter(lambda vals: vals[iteration] == '0', values)), iteration + 1)

def co2Generator(values, iteration):
    row = len(values)

    if row == 1:
        return list(map(int,values[0]))

    numOnes = 0
    for i in range(row):
        if values[i][iteration] == '1':
            numOnes += 1
    
    if numOnes < row/2:
        return co2Generator(list(filter(lambda vals: vals[iteration] == '1', values)), iteration + 1)
    else:
        return co2Generator(list(filter(lambda vals: vals[iteration] == '0', values)), iteration + 1)


with open("input.txt") as inputFile:
    # read inputs
    inputs = list(map(lambda line : line.strip(), inputFile.readlines()))
    print(bin2int(oxyGenerator(inputs,0)) * bin2int(co2Generator(inputs,0)))
