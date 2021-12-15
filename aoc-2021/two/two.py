# Part 1

with open(r"C:\Users\kavin\Documents\Programming\programming-problems\aoc-2021\two\input.txt", "r") as inputFile:
    position, depth = 0,0
    for line in inputFile:
        dist = line.split()[1]
        # print(dist)
        if line[0] == 'f':
            position += int(dist)
        elif line[0] == 'd':
            depth += int(dist)
        elif line[0] == 'u':
            depth -= int(dist)

print("Part 1: ", depth*position)

# Part 2
with open(r"C:\Users\kavin\Documents\Programming\programming-problems\aoc-2021\two\input.txt", "r") as inputFile:
    position, depth, aim = 0,0,0
    for line in inputFile:
        dist = line.split()[1]
        # print(dist)
        if line[0] == 'f':
            position += int(dist)
            depth += aim*int(dist)
        elif line[0] == 'd':
            aim += int(dist)
        elif line[0] == 'u':
            aim -= int(dist)

print("Part 2: ", depth*position)