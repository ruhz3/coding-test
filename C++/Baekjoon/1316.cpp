#include <iostream>
#include <string>
using namespace std;

int N;
string word;

int check() {
	int len = word.size();
	char key = word[0];
	bool flag;

	// 단어에 있는 알파벳을 순서대로 검사
	for (int i = 0; i < len; i++) {
		key = word[i];
		flag = false;

		// 연속으로 나온것은 제외, 한 번이라도 다른게 나왔다가 다시 나오면 0 반환
		for (int j = i+1; j < len; j++) {
			if (key == word[j] && flag)
				return 0;
			else if (key == word[j] && !flag)
				continue;
			else if (key != word[j])
				flag = true;
		}
	}

	// 그렇지 않았을 경우 1 반환
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