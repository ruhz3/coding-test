words = []
max_len = -1

for _ in range(5):
    words.append(word := input())
    max_len = max(max_len, len(word))

for idx in range(max_len):
    for word in words:
        try:
            print(word[idx], end="")
        except Exception:
            continue
