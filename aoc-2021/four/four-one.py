import numpy as np
import re


class Board:
    def __init__(self, nums):
        self.board = [
            [[nums[i][j], False] for j in range(5)]
            for i in range(5)
        ]

    def checkWin(self):
        for row in range(5):
            if all([self.board[row][i][1] for i in range(5)]):
                return True
        for col in range(5):
            if all([self.board[i][col][1] for i in range(5)]):
                return True
        return False
    
    def get_score(self, last_called):
        uncalled_sum = 0
        for row in range(5):
            for col in range(5):
                if not self.board[row][col][1]:
                    uncalled_sum += self.board[row][col][0]

        return uncalled_sum * last_called

    def mark(self, num):
        for row in self.board:
            for el in row:
                if el[0] == num:
                    el[1] = True

def parse_board(lines):
    return [[int(i) for i in re.split(" +", line.strip())] for line in lines]

with open("input.txt") as inputFile:
    data = inputFile.read().strip().split("\n")

    nums = [int(i) for i in data[0].split(",")]

    boards = []
    i = 2
    while i < len(data):
        x = parse_board(data[i:i+5])
        boards.append(Board(x))
        i += 6

    ans = None
    for x in nums:
        for b in boards:
            b.mark(x)
        for b in boards:
            if b.checkWin():
                ans = b.get_score(x)
                break

        if ans != None:
            break

    print(ans)
        