#include <iostream>
#include <cstring>
#include <queue>
using namespace std;
queue<int> answer;

int row, col;
int matrix[20][20];

int run() {
	/*
	 * 왼쪽 위 부터 탐색하여 비어있는 칸을 x,y에 저장
	 * flag 지정하여, 찾는 즉시 반복문 탈출
	 * x, y가 반복문을 그대로 통과하면 모두 채워졌다는 의미 >> 1을 반환(result에 더해짐)
	 */
	int x = -1;
	int y = -1;
	bool flag = false;	
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++)
			if (matrix[i][j] == 0) {
				x = i;
				y = j;
				flag = true;
				break;
			}
		if (flag == true)
			break;
	}
	if (x == -1) return 1;
	
	/*
	 * 모양에 맞게 x, y의 범위 비어있는지 여부를 검사
	 * 호출된 케이스 별로 각각의 성공 횟수가 result에 더해짐
	 * 실패 시 끝에서 부터 반환되며 재귀호출문 뒤의 문장들로 원상복귀
	 */
	bool flag2 = false;
	int result = 0;

	// ┘모양, └ 모양, ┌ 모양,  ┐모양 순서로 검사하고 각 경우 재귀 호출하여 탐색 
	if ((y > 0 && x < row - 1) && (matrix[x + 1][y] == 0 && matrix[x + 1][y - 1] == 0)) {
		matrix[x][y] = 1;
		matrix[x + 1][y] = 1;
		matrix[x + 1][y - 1] = 1;
		result += run();
		matrix[x][y] = 0;
		matrix[x + 1][y] = 0;
		matrix[x + 1][y - 1] = 0;
		flag2 = true;
	}
	if ((y < col - 1 && x < row - 1) && (matrix[x + 1][y] == 0 && matrix[x + 1][y + 1] == 0)) {
		matrix[x][y] = 1;
		matrix[x + 1][y] = 1;
		matrix[x + 1][y + 1] = 1;
		result += run();
		matrix[x][y] = 0;
		matrix[x + 1][y] = 0;
		matrix[x + 1][y + 1] = 0;
		flag2 = true;
	}
	if ((y < col - 1 && x < row - 1) && (matrix[x + 1][y] == 0 && matrix[x][y + 1] == 0)) {
		matrix[x][y] = 1;
		matrix[x][y + 1] = 1;
		matrix[x + 1][y] = 1;
		result += run();
		matrix[x][y] = 0;
		matrix[x][y + 1] = 0;
		matrix[x + 1][y] = 0;
		flag2 = true;
	}
	if ((y < col - 1 && x < row - 1) && (matrix[x][y + 1] == 0 && matrix[x + 1][y + 1] == 0)) {
		matrix[x][y] = 1;
		matrix[x][y + 1] = 1;
		matrix[x + 1][y + 1] = 1;
		result += run();
		matrix[x][y] = 0;
		matrix[x][y + 1] = 0;
		matrix[x + 1][y + 1] = 0;
		flag2 = true;
	}
	if (!flag2) return 0;

	return result;
}

int main()
{
	int tc;
	cin >> tc;
	while (tc > 0) {
		cin >> row >> col;
		memset(matrix, -1, sizeof(matrix));
		char input;
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				cin >> input;
				if (input == '#') {
					matrix[i][j] = -1;
				}
				else if (input == '.') {
					matrix[i][j] = 0;
					count++;
				}
				else {
					return -1;
				}
			}
		}
		if (count % 3 == 0)
			answer.push(run());
		else
			answer.push(0);
		tc--;
	}
	while (!answer.empty()) {
		cout << answer.front() << endl;
		answer.pop();
	}

	return 0;
}