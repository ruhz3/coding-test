from sys import stdin


def run(baskets: list[int], operations: list[list[int]]) -> None:
    def swap(baskets, op):
        x, y = op
        tmp = baskets[x]
        baskets[x] = baskets[y]
        baskets[y] = tmp

    for op in operations:
        swap(baskets, op)


if __name__ == "__main__":
    # 00. 입력을 받는다.
    n, m = map(int, stdin.readline().split(' '))
    operations = [list(map(int, stdin.readline().split(' '))) for _ in range(m)]
    baskets = list(range(n+1))

    # 01. 공 바꾸기를 수행한다.
    run(baskets, operations)

    # 02. 바뀐 배열을 출력한다.
    for ball in baskets[1:]:
        print(ball, end=" ")
