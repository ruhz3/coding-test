#include <iostream>
#include <string>
using namespace std;

int N;
string word;

int check() {
	int len = word.size();
	char key = word[0];
	bool flag;

	// �ܾ �ִ� ���ĺ��� ������� �˻�
	for (int i = 0; i < len; i++) {
		key = word[i];
		flag = false;

		// �������� ���°��� ����, �� ���̶� �ٸ��� ���Դٰ� �ٽ� ������ 0 ��ȯ
		for (int j = i+1; j < len; j++) {
			if (key == word[j] && flag)
				return 0;
			else if (key == word[j] && !flag)
				continue;
			else if (key != word[j])
				flag = true;
		}
	}

	// �׷��� �ʾ��� ��� 1 ��ȯ
	return 1;
}

int main() {
	cin >> N;
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		cin >> word;
		cnt += check();
	}
	cout << cnt << endl;

	return 0;
}