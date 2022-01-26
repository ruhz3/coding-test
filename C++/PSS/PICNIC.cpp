#include <iostream>
#include <queue>
using namespace std;
queue<int> answer;

// matrix�� ģ�� ����, paired�� ģ���� �ִ��� ���� ����
int matrix[10][10];
int paired[10];
int student_num, pair_num;

int makePair() {
	// ¦�� ���� �л��� �ִٸ� idx�� �����ϰ�, ��� ¦�� �ִٸ� 1�� ��ȯ
	int idx = -1;
	for (int i = 0; i < student_num; i++)
		if (paired[i] == -1) {
			idx = i;
			break;
		}
	if (idx == -1) return 1;

	// ¦�� ����, ģ���� �л��� ã�Ҵٸ� �����Ű�� ��� ȣ��
	int result = 0;
	for (int i = idx + 1; i < student_num; i++)
		if (paired[i] == -1 && matrix[idx][i] == 1) {
			paired[idx] = 1;
			paired[i] = 1;
			result += makePair();
			// �ش� �Լ��� �ٲ� �迭�� ������� �ǵ����� ��ȯ
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