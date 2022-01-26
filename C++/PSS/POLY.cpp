/* (�ڡڡ�)
 * ���簢�� Ÿ���� �̾���� ��,
 * ���η� ��ĭ�� ���� �̾ ���η� �����ϴ� ����� ��

 * In : �׽�Ʈ���̽� �� -> Ÿ���� ����(1 ~ 100)
 * Out : ����� ���� 1,000,000���� ���� ������ ���

 * TestCase :
 * 2 >> 0
 * 4 >> 19
 * 92 >> 4841817
 */

#include <iostream>
#include <algorithm>
#include <string>
#define VALUE 1000000
using namespace std;

int num;
int tile[100];
// cache[�ٿ��� �ϴ� Ÿ�� ��][���̴� Ÿ��]
int cache[101][101];

int run(int left_num, int curr_line) {
	// ��� Ż�� ����
	if (left_num == curr_line)
		return 1;
	else if (left_num < curr_line)
		return 0;

	// �޸������̼�
	int &ret = cache[left_num][curr_line];
	if (ret != -1) {
		return ret;
	}
	
	// ���� ������ �Ű� ���� �� �ִ� ����� ��
	int result = 0;
	int gap = 0;
	for (int i = 1; i <= left_num - curr_line; i++) {
		gap = abs(curr_line + i - 1);
		result += (run(left_num - curr_line, i) * gap) % VALUE;
	}

	return ret = result % VALUE;
}

int main() {
	int result = 0;
	cin >> num;
	memset(tile, -1, sizeof(cache));
	for (int i = 1; i <= num; i++) {
		result += run(num, i);
	}
	cout << result % VALUE << endl;
	return 0;
}