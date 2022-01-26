#include <iostream>

using namespace std;

int num;
int numArray[11] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
int opCount[4] = { 0, 0, 0, 0 };
int max = -2147483647;
int min = 2147483647;


//재귀, idx = 넘겨받은 숫자의 인덱스, result = 현재까지 연산한 결과값
void run(int idx, int result) {
	//최종 단계인 경우 return
	if (idx == num - 1) {
		//최대인가
		if (result > max)
			max = result;
		//최소인가
		if (result < min)
			min = result;
		return;
	}

	//덧셈으로 재귀
	if (opCount[0] != 0) {
		opCount[0]--;
		run(idx + 1, result + numArray[idx + 1]);
		opCount[0]++;
	}
	//현재 연산자를 뺄셈으로 재귀
	if (opCount[1] != 0) {
		opCount[1]--;
		run(idx + 1, result - numArray[idx + 1]);
		opCount[1]++;
	}
	//현재 연산자를 곱셈으로 재귀
	if (opCount[2] != 0) {
		opCount[2]--;
		run(idx + 1, result * numArray[idx + 1]);
		opCount[2]++;
	}
	//현재 연산자를 나눗셈으로 재귀
	if (opCount[3] != 0) {
		opCount[3]--;
		run(idx + 1, result / numArray[idx + 1]);
		opCount[3]++;
	}
}

int main() {
	cin >> num;
	for (int i = 0; i < num; i++) {
		cin >> numArray[i];
	}
	for (int i = 0; i < 4; i++) {
		cin >> opCount[i];
	}
	run(numArray[0], 0);
	cout << max << endl;
	cout << min << endl;
	return 0;
}