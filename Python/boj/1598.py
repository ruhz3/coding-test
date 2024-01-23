from sys import stdin


def run(a: int, b: int) -> int:
    def pos(num):
        return (num+3) % 4, int((num-1)/4)

    ar, ac = pos(a)
    br, bc = pos(b)
    return abs(ar-br) + abs(ac-bc)


if __name__ == "__main__":
    a, b = map(int, stdin.readline().split(' '))
    result = run(a, b)
    print(result)


