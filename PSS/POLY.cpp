/* (★★☆)
 * 정사각형 타일을 이어붙일 때,
 * 가로로 빈칸이 없게 이어서 세로로 연결하는 경우의 수

 * In : 테스트케이스 수 -> 타일의 갯수(1 ~ 100)
 * Out : 경우의 수를 1,000,000으로 나눈 나머지 출력

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
// cache[붙여야 하는 타일 수][붙이는 타일]
int cache[101][101];

int run(int left_num, int curr_line) {
	// 재귀 탈출 조건
	if (left_num == curr_line)
		return 1;
	else if (left_num < curr_line)
		return 0;

	// 메모이제이션
	int &ret = cache[left_num][curr_line];
	if (ret != -1) {
		return ret;
	}
	
	// 이전 조각을 옮겨 붙일 수 있는 방법의 수
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