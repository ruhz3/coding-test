num = int(input())

width = 2
while num > 0:
    width += width-1
    num -= 1

print(width**2)
