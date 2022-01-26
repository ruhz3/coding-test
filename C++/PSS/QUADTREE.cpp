#include <iostream>
#include <string>
#include <queue>
using namespace std;
queue<string> answer;

string input;

string reverse(int idx) {
	// ���� ó�� : ù �Է� 'x', �Է� ���� 1
	if (idx == 0 && input.at(0) == 'x')
		return "x" + reverse(1);
	if (input.length() == 1)
		return input;

	// idx ���� 4���� ������ ����, ���� ��Ų ���� return
	int cnt = 0;
	string * S = new string[4];
	while (cnt < 4) {
		if (input.at(idx + cnt) == 'w') {
			S[cnt++] = "w";
		}
		else if(input.at(idx + cnt) == 'b') {
			S[cnt++] = "b";
		}
		// 'x'�� ���� ��� �ڿ� ���� 4���� ������ 1���� �����ش�.
		else {
			S[cnt] = "x" + reverse(idx + cnt + 1);
			idx += S[cnt++].length() - 1;
		}
	}
	return S[2] + S[3] + S[0] + S[1];
}

int main() {
	int tc;
	cin >> tc;
	while (tc > 0) {
		cin >> input;
		answer.push(reverse(0));
		tc--;
	}
	while (!answer.empty()) {
		cout << answer.front() << endl;
		answer.pop();
	}
	return 0;
}