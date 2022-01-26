#include <iostream>
#include <math.h>
#include <algorithm>
#include <string>
using namespace std;

int n, m;
int numArr[100];
int cache[101][11];

int cal(int start, int end) {
	double ret = 0;
	for (int i = start; i <= end; ++i)
		ret += (double)numArr[i];
	ret /= (double)(end - start + 1);
	ret = floor(ret + 0.5);

	int mid = (int)ret;
	int result = 0;
	for (int i = start; i <= end; ++i)
		result += (numArr[i] - mid)*(numArr[i] - mid);

	return result;
}

int run(int idx, int last) {
	int &ret = cache[idx][last];
	if (ret != -1)
		return ret;

	if (last == 0) {
		if (idx == n)
			return 0;
		else
			return 9999999;
	}
	else if (idx >= n) {
		return 0;
	}

	int result = 9999999;
	for (int i = idx; i < n; ++i) {
		result = min(result, cal(idx, i) + run(i + 1, last - 1));
	}

	return ret = result;
}

int main() {
	int tc;
	cin >> tc;
	string result;
	while (tc > 0) {
		memset(cache, -1, sizeof(cache));
		cin >> n >> m;
		for (int i = 0; i < n; ++i)
			cin >> numArr[i];
		sort(numArr, numArr + n);
		result += to_string(run(0, m)) + "\n";
		tc--;
	}
	return 0;
}


