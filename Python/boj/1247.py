from sys import stdin

result = ""
for _ in range(3):
    n = int(stdin.readline())
    test_case = [int(stdin.readline()) for _ in range(n)]

    test_case_sum = sum(test_case)
    if test_case_sum == 0:
        result += '0\n'
    elif test_case_sum > 0:
        result += '+\n'
    else:
        result += '-\n'

print(result)