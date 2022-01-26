#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

string PI;
int cache[10000];

int levelCheck(int start, int end) {
	string Text = PI.substr(start, (end - start) + 1);

	//난이도 1
	bool bflag_1 = true;
	for (int i = 0; i < Text.length(); i++)
		if (Text[0] != Text[i]) {
			bflag_1 = false;
			break;
		}

	if (bflag_1) return 1;

	//난이도 2
	bool bflag_2 = true;
	if (Text[1] - Text[0] == -1 || Text[1] - Text[0] == 1) {
		for (int i = 2; i < Text.length(); i++)
			if ((Text[1] - Text[0]) != (Text[i] - Text[i - 1])) {
				bflag_2 = false;
				break;
			}
	}
	if (bflag_2) return 2;

	//난이도 4
	bool bflag_4 = true;
	for (int i = 0, j = 1; i < Text.length() && j < Text.length(); i += 2, j += 2)
		if (Text[0] != Text[i] || Text[1] != Text[j]) {
			bflag_4 = false;
			break;
		}
	if (bflag_4) return 4;

	//난이도 5
	bool bflag_5 = true;
	for (int i = 2; i < Text.length(); i++)
		if ((Text[1] - Text[0]) != (Text[i] - Text[i - 1])) {
			bflag_5 = false;
			break;
		}
	if (bflag_5) return 5;


	//난이도 10
	return 10;
}
int run(int idx) {
	if (idx == PI.length())
		return 0;

	int& ret = cache[idx];
	if (ret != -1)
		return ret;

	ret = 9999999;
	int next;
	for (int piece_length = 3; piece_length <= 5; piece_length++) {
		if (idx + piece_length <= PI.length()) {
			next = run(idx + piece_length) + levelCheck(idx, idx + piece_length - 1);
			ret = min(ret, next);
		}
	}

	return ret;
}
int main() {
	int tc;
	cin >> tc;

	string result = "\n";
	while (tc > 0) {
		cin >> PI;
		memset(cache, -1, sizeof(cache));
		result += to_string(run(0)) + "\n";
		tc--;
	}

	cout << result << endl;

	return 0;
}