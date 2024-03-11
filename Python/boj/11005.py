num, system = map(int, input().split(' '))

result = ""
level = 0

while num > 0:
    new_num = num % system
    if new_num >= 10:
        result += chr(ord('A') + new_num - 10)
    else:
        result += str(new_num)
    num = int(num/system)

print(result[::-1])
