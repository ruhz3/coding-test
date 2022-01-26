/*
 3			
 he?p	 *p*	  *bb*
 3		 3		  1
 help	 help	  babbbc
 heap	 papa
 helpp	 hello
 */
#include <iostream>
#include <algorithm>
#include <string>
using namespace std;

string WildCard, Input;
int cache[101][101];

bool run(int idx_wild, int idx_input) {
	int &ret = cache[idx_wild][idx_input];

	if (ret != -1)
		return ret;

	while (idx_wild < WildCard.length() && idx_input < Input.length() && WildCard[idx_wild] == '?' || Input[idx_input] == WildCard[idx_wild]) {
		idx_wild++;
		idx_input++;
	}
	if (idx_wild == WildCard.length()) {
		return ret = (idx_input == Input.length());
	}

	if (WildCard.at(idx_wild) == '*') {
		for (int skip = 0; skip + idx_input <= Input.length(); skip++)
			if (run(idx_wild + 1, idx_input + skip))
				return ret = 1;
	}
	return ret = 0;
}

int main() {
	int tc;
	cin >> tc;

	string Result = "";
	int inputNum;
	while (tc > 0) {
		cin >> WildCard;
		cin >> inputNum;
		for (int i = 0; i < inputNum; i++) {
			cin >> Input;
			memset(cache, -1, sizeof(cache));
			if (run(0, 0))
				Result += Input + "\n";
		}

		WildCard = ""; Input = "";
		tc--;
	}

	cout << Result << endl;

	return 0;
}