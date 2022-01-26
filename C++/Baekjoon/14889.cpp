#include <iostream>

using namespace std;

int num;
int numArray[11] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
int opCount[4] = { 0, 0, 0, 0 };
int max = -2147483647;
int min = 2147483647;


//���, idx = �Ѱܹ��� ������ �ε���, result = ������� ������ �����
void run(int idx, int result) {
	//���� �ܰ��� ��� return
	if (idx == num - 1) {
		//�ִ��ΰ�
		if (result > max)
			max = result;
		//�ּ��ΰ�
		if (result < min)
			min = result;
		return;
	}

	//�������� ���
	if (opCount[0] != 0) {
		opCount[0]--;
		run(idx + 1, result + numArray[idx + 1]);
		opCount[0]++;
	}
	//���� �����ڸ� �������� ���
	if (opCount[1] != 0) {
		opCount[1]--;
		run(idx + 1, result - numArray[idx + 1]);
		opCount[1]++;
	}
	//���� �����ڸ� �������� ���
	if (opCount[2] != 0) {
		opCount[2]--;
		run(idx + 1, result * numArray[idx + 1]);
		opCount[2]++;
	}
	//���� �����ڸ� ���������� ���
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