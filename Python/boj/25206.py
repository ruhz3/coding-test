from sys import stdin


GRADE_SCORE = {
    "A+": 4.5,
    "A0": 4.0,
    "B+": 3.5,
    "B0": 3.0,
    "C+": 2.5,
    "C0": 2.0,
    "D+": 1.5,
    "D0": 1.0,
    "F": 0.0
}

score_sum = 0
point_sum = 0

for _ in range(20):
    _, point, grade = stdin.readline().split(' ')
    point = float(point)
    grade = grade.strip()

    if grade == "P":
        continue

    grade_as_score = GRADE_SCORE[grade]
    score_sum += point * grade_as_score
    point_sum += point

print("%.6f" % (score_sum/point_sum))
