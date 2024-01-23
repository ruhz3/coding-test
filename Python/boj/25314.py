from sys import stdin


def run(n: int) -> str:
    return "long " * int(n/4) + "int"


if __name__ == "__main__":
    n = int(stdin.readline())
    result = run(n)
    print(result)
