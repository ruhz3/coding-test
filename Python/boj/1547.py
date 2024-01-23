from sys import stdin


def run(swaps: list) -> int:
    cup_over_ball = 1
    for x, y in swaps:
        if x == cup_over_ball:
            cup_over_ball = y
        elif y == cup_over_ball:
            cup_over_ball = x
    return cup_over_ball


if __name__ == "__main__":
    m = int(stdin.readline())
    ops = []
    for _ in range(m):
        input_str = stdin.readline()
        op = [int(pos) for pos in input_str.split(' ')]
        ops.append(op)
    result = run(ops)
    print(result)
