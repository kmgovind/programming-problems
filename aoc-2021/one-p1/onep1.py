data = [int(x) for x in open("input.txt")]
print("Part 1:", sum([data[i] > data[i-1] for i in range(1, len(data))]))

