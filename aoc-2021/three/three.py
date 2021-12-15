# Part 1

# with open(r"C:\Users\kavin\Documents\Programming\programming-problems\aoc-2021\three\sample.txt", "r") as inputFile:
#     for line in inputFile:
#         print(line)

with open("sample.txt") as inputFile:
    for line in inputFile:
        print(line)

# print("Part 1: ", depth*position)

# # Part 2
# with open(r"C:\Users\kavin\Documents\Programming\programming-problems\aoc-2021\three\input.txt", "r") as inputFile:
#     position, depth, aim = 0,0,0
#     for line in inputFile:
#         dist = line.split()[1]
#         # print(dist)
#         if line[0] == 'f':
#             position += int(dist)
#             depth += aim*int(dist)
#         elif line[0] == 'd':
#             aim += int(dist)
#         elif line[0] == 'u':
#             aim -= int(dist)

# print("Part 2: ", depth*position)