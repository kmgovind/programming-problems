values = [int(x) for x in open(r"C:\Users\kavin\Documents\Programming\programming-problems\aoc-2021\one-p2\input.txt", "r")]
print("Num Increasing:", sum([values[i] > values[i-3] for i in range(3, len(values))]))
    