from sys import stdin


def solution(num: int) -> int:
    word_width = 1
    while num > 0:
        last_num = num % 10
        if last_num == 0:
            word_width += 5
        elif last_num == 1:
            word_width += 3
        else:
            word_width += 4
        num = int(num/10)

    return word_width


if __name__ == "__main__":
    result = ""
    while (n := int(stdin.readline())) != 0:
        result += f'{solution(n)}\n'

    print(result)
