text = input()
reversed_text = reversed(text)

is_palindrome = True
for idx, char in enumerate(reversed(text)):
    if text[idx] != char:
        is_palindrome = False
        break

print(int(is_palindrome))
