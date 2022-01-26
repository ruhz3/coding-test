#include <iostream>
#include <cstring>
#include <queue>
using namespace std;
queue<int> answer;

int row, col;
int matrix[20][20];

int run() {
	/*
	 * ���� �� ���� Ž���Ͽ� ����ִ� ĭ�� x,y�� ����
	 * flag �����Ͽ�, ã�� ��� �ݺ��� Ż��
	 * x, y�� �ݺ����� �״�� ����ϸ� ��� ä�����ٴ� �ǹ� >> 1�� ��ȯ(result�� ������)
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
	 * ��翡 �°� x, y�� ���� ����ִ��� ���θ� �˻�
	 * ȣ��� ���̽� ���� ������ ���� Ƚ���� result�� ������
	 * ���� �� ������ ���� ��ȯ�Ǹ� ���ȣ�⹮ ���� ������ ���󺹱�
	 */
	bool flag2 = false;
	int result = 0;

	// �����, �� ���, �� ���,  ����� ������ �˻��ϰ� �� ��� ��� ȣ���Ͽ� Ž�� 
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