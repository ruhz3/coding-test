

def draw(size: int) -> str:
    max_length = 2 * size - 1
    result = ""

    for i in range(size):
        star_num = 2 * i + 1
        space_num = int((max_length - star_num) / 2)
        result += f'{" " * space_num}{"*" * star_num}\n'

    for i in range(size - 1):
        space_num = i + 1
        star_num = max_length - 2 * space_num
        result += f'{" " * space_num}{"*" * star_num}\n'

    return result


if __name__ == "__main__":
    n = int(input())
    stars = draw(n)
    print(stars)
