#include <iostream>
#include <cstring>
using namespace std;

int map[15];
int N;

bool check(int idx, int n) {
	for (int i = 0; i < idx; i++) {
		if (n == map[i] || abs(idx - i) == abs(map[i] - n))
			return false;
	}
	return true;
}

int setQueen(int idx) {
	if (idx == N)
		return 1;

	int res = 0;
	for (int i = 0; i < N; i++) {
		if (check(idx, i)) {
			map[idx] = i;
			res += setQueen(idx + 1);
		}
	}
	return res;
}

int main() {
	cin >> N;
	memset(map, -1, sizeof(map));
	cout << setQueen(0) << endl;
	return 0;
}