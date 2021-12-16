values = [int(x) for x in open("input.txt")]
print("Num Increasing:", sum([values[i] > values[i-3] for i in range(3, len(values))]))
    