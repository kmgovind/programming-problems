data = [int(x) for x in open(r"C:\Users\kavin\Documents\Programming\programming-problems\aoc-2021\one-p1\input.txt", "r")]
print("Part 1:", sum([data[i] > data[i-1] for i in range(1, len(data))]))

