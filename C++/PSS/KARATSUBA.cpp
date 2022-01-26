#include <iostream>
#include <algorithm>
#include <math.h>
using namespace std;

int karatsuba(int num1, int num2) {
	// �� �ڸ����� �Ǹ� �׳� ����
	if (num1 < 10 || num2 < 10)
		return num1 * num2;

	// ���ڸ� ������ m�� �� �� �� ���� �ڸ����� �������� ����
	int m;
	m = min((int)(log10(num1) + 1), (int)(log10(num2) + 1));
	m /= 2;

	// num�� high(�պκ�), low(�޺κ�)���� ����
	int high1, low1;
	high1 = num1 / pow(10, m);
	low1 = num1 % (int)pow(10, m);
	int high2, low2;
	high2 = num2 / pow(10, m);
	low2 = num2 % (int)pow(10, m);

	// z0, z1, z2�� ���� ���ȣ��
	int z0 = karatsuba(low1, low2);
	int z1 = karatsuba((low1 + high1), (low2 + high2));
	int z2 = karatsuba(high1, high2);

	return (z2 * pow(10, (m * 2)) + ((z1 - z2 - z0) * pow(10, m)) + z0);
}

int main() {
	int A, B;
	cin >> A >> B;
	cout << karatsuba(A, B) << endl;
	return 0;
}