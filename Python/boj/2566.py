SIZE = 9

nums = [list(map(int, input().split())) for _ in range(SIZE)]

max_num = -1
max_index = (-1, -1)
for i in range(SIZE):
    for j in range(SIZE):
        num = nums[i][j]
        if max_num < num:
            max_num = num
            max_index = (i, j)

print(max_num)
print(max_index[0]+1, max_index[1]+1)
