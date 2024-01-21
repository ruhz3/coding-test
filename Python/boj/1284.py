from sys import stdin


result = ""

while (n := int(stdin.readline())) != 0:
    word_width = 1
    while n > 0:
        last_num = n % 10
        if last_num == 0:
            word_width += 5
        elif last_num == 1:
            word_width += 3
        else:
            word_width += 4
        n = int(n/10)

    result += f"{word_width}\n"

print(result)
