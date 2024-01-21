from sys import stdin

n = stdin.readline()
call_times = [int(t) for t in stdin.readline().split()]

y_charge = 0
m_charge = 0

for x in call_times:
    y_charge += int(x/30 + 1) * 10
    m_charge += int(x/60 + 1) * 15

if y_charge == m_charge:
    print(f'Y M {y_charge}')
elif y_charge > m_charge:
    print(f'M {m_charge}')
else:
    print(f'Y {y_charge}')
