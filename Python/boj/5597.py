from sys import stdin

submitted = [False] * 30

for _ in range(28):
    num = int(stdin.readline())-1
    submitted[num] = True

for num in range(30):
    if not submitted[num]:
        print(num+1)
