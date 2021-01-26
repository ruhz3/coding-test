#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;

int row, col;
int map[8][8];
bool check[8][8];

/*감염*/
void infection(int R, int C) {
	if (R > 0 && !check[R - 1][C] && map[R - 1][C] == 0) {
		check[R - 1][C] = true;
		infection(R - 1, C);
	}
	if (R < row && !check[R + 1][C] && map[R + 1][C] == 0) {
		check[R + 1][C] = true;
		infection(R + 1, C);
	}
	if (C > 0 && !check[R][C - 1] && map[R][C - 1] == 0) {
		check[R][C - 1] = true;
		infection(R, C - 1);
	}
	if (C < col && !check[R][C + 1] && map[R][C + 1] == 0) {
		check[R][C + 1] = true;
		infection(R, C + 1);
	}
}

/*벽 세우기*/
int setWall(int x, int y, int count) {
	int result = 0;
	// 벽을 3개 모두 세우면,
	if (count == 3) {
		// 바이러스 찾아서 감염함수 호출하고
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i][j] == 2) infection(i, j);
			}
		}
		// 안전지역 검사해 넓이 반환
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (map[i][j] == 0 && !check[i][j]) result++;
		memset(check, false, sizeof(check));
		return result;
	}

	// 벽을 세우는 경우 재귀호출
	int R, C;
	for (int i = col * x + y; i < row * col; i++) {
		R = i / col; C = i % col;
		if (map[R][C] == 0) {
			map[R][C] = 1;
			result = max(result, setWall(R, C, count + 1));
			map[R][C] = 0;
		}
	}
	return result;
}

int main() {
	cin >> row >> col;
	memset(map, 1, sizeof(map));
	memset(check, false, sizeof(check));
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			cin >> map[i][j];
		}
	}
	cout << setWall(0, 0, 0) << endl;
	return 0;
}