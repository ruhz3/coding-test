#include <iostream>
#include <algorithm>
#include <math.h>
using namespace std;

int karatsuba(int num1, int num2) {
	// 한 자릿수가 되면 그냥 연산
	if (num1 < 10 || num2 < 10)
		return num1 * num2;

	// 숫자를 분할할 m을 둘 중 더 작은 자릿수의 절반으로 설정
	int m;
	m = min((int)(log10(num1) + 1), (int)(log10(num2) + 1));
	m /= 2;

	// num을 high(앞부분), low(뒷부분)으로 분할
	int high1, low1;
	high1 = num1 / pow(10, m);
	low1 = num1 % (int)pow(10, m);
	int high2, low2;
	high2 = num2 / pow(10, m);
	low2 = num2 % (int)pow(10, m);

	// z0, z1, z2를 각각 재귀호출
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