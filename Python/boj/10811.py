from sys import stdin


def reverse(arr: list[int], start: int, end: int) -> None:
    def swap(a: int, b: int) -> None:
        tmp = arr[a]
        arr[a] = arr[b]
        arr[b] = tmp

    swap_times = int((end-start+1)/2)
    for k in range(swap_times):
        swap(start+k, end-k)


if __name__ == "__main__":
    n, m = map(int, stdin.readline().split(' '))
    baskets = list(range(n+1))
    for _ in range(m):
        i, j = map(int, stdin.readline().split(' '))
        reverse(baskets, i, j)

    baskets.pop(0)
    for basket in baskets:
        print(basket, end=' ')