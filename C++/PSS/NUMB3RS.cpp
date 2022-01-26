/* (�ڡڡ�)
 * N���� ������ �����Ұ� �ִ� P���� ���� ������, �Ϸ翡 1�� �̵���
 * Ż�� ������ P���� ������ ������ �̵�
 * D�� �� �������� ���� Ȯ���� ����ϴ� ���α׷��� �ۼ�

 * In : �׽�Ʈ���̽� �� -> ������ ��(2<=N<=50) -> ���� �� ��(1<=D<=100) -> �����Ұ� �ִ� ���� ��ȣ(P)
 *      -> ���� ������ ����(N��N���) -> ����� ���� ����(T) -> ����� ���� ��ȣ(Q)
 * Out : Ȯ��

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
	// ��� Ż�� ����
	if (day == D) {
		if (town == Q) return 1.0;
		else return 0.0;
	}

	// �޸������̼�
	double &ret = cache[town][day];
	if (ret != -1.0)
		return ret;

	// ���ȣ���ϸ� Ȯ���� ���ذ��� ���
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