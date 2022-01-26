#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;

int map[500][500];
int cache[501][501];
int N;

int findMaxWay(int row, int col) {
	int &ret = cache[row][col];
	// ���� ��� : �ﰢ���� ���� ����
	if (row == N - 1) {
		return ret = map[row][col];
	}
	// �޸������̼�
	if (ret != -1) {
		return ret;
	}
	// �� ���� ���߿��� �� ū ���� ã�� ����
	ret = map[row][col];
	int tmp = -1;
	for (int i = col; i <= col + 1; i++) {
		tmp = max(tmp, findMaxWay(row + 1, i));
	}
	ret += tmp;
	return ret;
}

int main() {
	cin >> N;
	memset(cache, -1, sizeof(cache));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j <= i; j++) {
			cin >> map[i][j];
		}
	}
	cout << findMaxWay(0, 0) << endl;

	return 0;
}
