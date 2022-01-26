/* (�ڡ١�)
 * 2��n (1<= n <= 100) ũ���� ���簢����
 * 2��1 �Ǵ� 1��2 ũ���� Ÿ�Ϸ� ä���ִ� ����� ��
 * ��, ä���־��� �� �¿� ��Ī�� ���� ����
 
 * In : �׽�Ʈ���̽� �� -> �簢���� �ʺ�
 * Out : ����� ���� 1,000,000,007�� ���� ������ ���

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

//����� ���� ���� �Լ�, �������� �������� Ǯ��
long long run(int idx) {
	// ��� Ż�� ����
	if (idx == -1) return 1;
	else if (idx == -2) return 0;

	// �̹� ����� ���� �ִٸ� �ٷ� ĳ�� return
	long long &ret = cache[idx];
	if (ret != -1) {
		cout << "Using memoization! (" << idx << ", " << ret << ")" << endl;
		return ret;
	}

	// ��ȭ�� = �Ǻ���ġ
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
		// ¦���� ���, ��� 2ĭ ¥�� ���� ���� �������� �������� ���� ����
		if (width % 2 == 0)
			result += to_string((run(width - 1) - (cache[width / 2 - 1] + cache[width / 2 - 2])));
		// Ȧ���� ���, ��� 1ĭ ���� ���� ��츦 ����
		else
			result += to_string((run(width - 1) - cache[width/2 - 1]));
		test_case--;
	}

	cout << result << endl;
	return 0;
}