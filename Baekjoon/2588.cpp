#include <iostream>
#include <math.h>
using namespace std;

int main() {
	int A, B;
	cin >> A >> B;

	int num;
	int sum = 0;
	for (int div = 1; div < 1000; div *= 10) {
		num = (B % (div * 10)) / div;
		cout << A * num << endl;
		sum += A * num * div;
	}
	cout << sum << endl;

	return 0;
}
