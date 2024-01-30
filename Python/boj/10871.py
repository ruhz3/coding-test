from sys import stdin

_, x = map(int, stdin.readline().split(' '))
arr = map(int, stdin.readline().split(' '))
for elem in arr:
    if elem < x:
        print(elem, end=" ")
