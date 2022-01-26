#include <iostream>
#include <queue>
using namespace std;
queue<int> answer;

// matrix에 친구 관계, paired에 친구가 있는지 여부 저장
int matrix[10][10];
int paired[10];
int student_num, pair_num;

int makePair() {
	// 짝이 없는 학생이 있다면 idx에 저장하고, 모두 짝이 있다면 1을 반환
	int idx = -1;
	for (int i = 0; i < student_num; i++)
		if (paired[i] == -1) {
			idx = i;
			break;
		}
	if (idx == -1) return 1;

	// 짝이 없고, 친구인 학생을 찾았다면 연결시키고 재귀 호출
	int result = 0;
	for (int i = idx + 1; i < student_num; i++)
		if (paired[i] == -1 && matrix[idx][i] == 1) {
			paired[idx] = 1;
			paired[i] = 1;
			result += makePair();
			// 해당 함수가 바꾼 배열을 원래대로 되돌리며 반환
			paired[idx] = -1; 
			paired[i] = -1;
		}
	return result;
}

int main() {
	int tc;
	cin >> tc;
	while (tc > 0) {
		cin >> student_num >> pair_num;
		for (int i = 0; i < 10; i++) {
			paired[i] = -1;
			for (int j = 0; j < 10; j++)
				matrix[i][j] = -1;
		}
		for (int i = 0; i < pair_num; i++){
			int a, b;
			cin >> a >> b;
			matrix[a][b] = 1;
			matrix[b][a] = 1;
		}
		answer.push(makePair(n));
		tc--;
	}

	while (!answer.empty()) {
		cout << answer.front() << endl;
		answer.pop();
	}

	return 0;
}