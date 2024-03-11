from sys import stdin

n, m = map(int, stdin.readline().split(' '))
matrix = [list(map(int, stdin.readline().split(' '))) for _ in range(n)]

for r in range(n):
    row = list(map(int, stdin.readline().split(' ')))
    for c in range(m):
        print(matrix[r][c] + row[c], end=' ')
    print()
