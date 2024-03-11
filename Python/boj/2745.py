from sys import stdin

num, b = stdin.readline().split(' ')

sum = 0
level = len(num)-1
system = int(b)

for char in num:
    try:
        n = int(char)
    except Exception:
        n = ord(char) - ord('A') + 10

    sum += n * (system ** level)
    level -= 1

print(sum)
    