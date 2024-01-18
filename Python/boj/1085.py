raw_input = input().split(' ')
x, y, w, h = [int(p) for p in raw_input]

h_distance = min(x, abs(x-w))
v_distance = min(y, abs(y-h))

print(min(h_distance, v_distance))
