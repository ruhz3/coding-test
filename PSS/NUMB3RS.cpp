/* (★★☆)
 * N개의 마을을 교도소가 있는 P마을 부터 시작해, 하루에 1씩 이동함
 * 탈출 당일은 P마을 인접한 마을로 이동
 * D일 후 각마을에 있을 확률을 계산하는 프로그램을 작성

 * In : 테스트케이스 수 -> 마을의 수(2<=N<=50) -> 지난 일 수(1<=D<=100) -> 교도소가 있는 마을 번호(P)
 *      -> 마을 사이의 연결(N×N행렬) -> 계산할 마을 갯수(T) -> 계산할 마을 번호(Q)
 * Out : 확률

 * TestCase :
 * 5 2 0
 * 0 1 1 1 0
 * 1 0 0 0 1
 * 1 0 0 0 0
 * 1 0 0 0 0
 * 0 1 0 0 0
 * 3
 * 0 2 4
 * >> 0.833333 0.000000 0.16666667
 */

#include <iostream>
#include <algorithm>
#include <string>

#define VALUE 1000000
using namespace std;

int num;
int map[51][51];
double townSum[51];
double cache[51][101];

int N, D, P, T, Q;

void makeTownSum() {
	memset(townRoads, 0, sizeof(townRoads));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++)
			townRoads[i] += map[i][j];
	}
}


double run(int town, int day) {
	// 재귀 탈출 조건
	if (day == D) {
		if (town == Q) return 1.0;
		else return 0.0;
	}

	// 메모이제이션
	double &ret = cache[town][day];
	if (ret != -1.0)
		return ret;

	// 재귀호출하며 확률을 더해가며 계산
	ret = 0.0;
	for (int i = 0; i < N; i++) {
		if (map[town][i] == 1)
			ret += run(i, day + 1) / townSum[i];
	}
	return ret;
}

int main() {
	int tc;
	cin >> tc;
	
	string result = "";
	while (tc > 0) {
		cin >> N >> D >> P;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				cin >> map[i][j];
		}
		makeTownSum();
		cin >> T;
		for (int i = 0; i < T; i++) {
			cin >> Q;
			for (int a = 0; a < 51; a++) {
				for (int b = 0; b < 101; b++)
					cache[a][b] = -1;
			}
			result += to_string(run(P, 0)) + "\n";
		}
		tc--;
	}
	cout << result << endl;
	return 0;
}