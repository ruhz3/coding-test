from sys import stdin, stdout


def run(arr: list, start: int, end: int, value: int) -> list[int]:
    for index in range(start, end + 1):
        arr[index] = value

    return arr


if __name__ == "__main__":
    n, m = map(int, stdin.readline().split(' '))
    baskets = [0]*(n+1)
    for _ in range(m):
        i, j, k = map(int, stdin.readline().split(' '))
        run(baskets, i, j, k)

    for i in range(1, n+1):
        stdout.write(str(baskets[i])+' ')

    stdout.flush()
