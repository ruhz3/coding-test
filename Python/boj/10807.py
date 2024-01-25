from sys import stdin


def run(nums: list[int], target: int) -> int:
    return nums.count(target)


if __name__ == "__main__":
    n = int(stdin.readline())
    numbers = [int(n) for n in stdin.readline().split(' ')]
    target = int(stdin.readline())

    result = run(numbers, target)
    print(result)
