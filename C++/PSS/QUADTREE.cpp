#include <iostream>
#include <string>
#include <queue>
using namespace std;
queue<string> answer;

string input;

string reverse(int idx) {
	// 예외 처리 : 첫 입력 'x', 입력 길이 1
	if (idx == 0 && input.at(0) == 'x')
		return "x" + reverse(1);
	if (input.length() == 1)
		return input;

	// idx 부터 4개의 조각을 세어, 반전 시킨 값을 return
	int cnt = 0;
	string * S = new string[4];
	while (cnt < 4) {
		if (input.at(idx + cnt) == 'w') {
			S[cnt++] = "w";
		}
		else if(input.at(idx + cnt) == 'b') {
			S[cnt++] = "b";
		}
		// 'x'가 나온 경우 뒤에 나온 4개의 조각을 1개로 묶어준다.
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