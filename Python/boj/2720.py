
QUARTER = 25
DIME = 10
NICKEL = 5
PENNY = 1

for _ in range(int(input())):
    change = int(input())
    print(change//QUARTER, end=" ")
    change %= QUARTER
    print(change//DIME, end=" ")
    change %= DIME
    print(change//NICKEL, end=" ")
    change %= NICKEL
    print(change)





