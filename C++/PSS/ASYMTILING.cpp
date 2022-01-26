/* (★☆☆)
 * 2×n (1<= n <= 100) 크기의 직사각형을
 * 2×1 또는 1×2 크기의 타일로 채워넣는 경우의 수
 * 단, 채워넣었을 때 좌우 대칭인 경우는 제외
 
 * In : 테스트케이스 수 -> 사각형의 너비
 * Out : 경우의 수를 1,000,000,007로 나눈 나머지 출력

 * TestCase : 
 * 2 >> 0
 * 4 >> 2
 * 92 >> 913227494
 */

#include <iostream>
#include <algorithm>
#include <string>
#define VALUE 1000000007

using namespace std;

int width;
int tile[100];
long long cache[101];

//경우의 수를 세는 함수, 여집합의 개념으로 풀이
long long run(int idx) {
	// 재귀 탈출 조건
	if (idx == -1) return 1;
	else if (idx == -2) return 0;

	// 이미 계산한 적이 있다면 바로 캐시 return
	long long &ret = cache[idx];
	if (ret != -1) {
		cout << "Using memoization! (" << idx << ", " << ret << ")" << endl;
		return ret;
	}

	// 점화식 = 피보나치
	long long result = 0;
	result += (run(idx - 1) + VALUE) % VALUE;
	result += (run(idx - 2) + VALUE) % VALUE;

	return ret = result % VALUE;
}

int main() {
	int test_case;
	cin >> test_case;
	string result = "";
	while (test_case > 0) {
		cin >> width;
		memset(cache, -1, sizeof(cache));
		// 짝수인 경우, 가운데 2칸 짜리 들어갔을 때와 절반으로 나뉘었을 때를 빼줌
		if (width % 2 == 0)
			result += to_string((run(width - 1) - (cache[width / 2 - 1] + cache[width / 2 - 2])));
		// 홀수인 경우, 가운데 1칸 들어갔을 때의 경우를 빼줌
		else
			result += to_string((run(width - 1) - cache[width/2 - 1]));
		test_case--;
	}

	cout << result << endl;
	return 0;
}