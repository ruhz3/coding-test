#include <iostream>
#include <cstring>
#include <string>
#include <algorithm>
#include <queue>
#define MAX 20000
using namespace std;
queue<int> answer;

int member[MAX];
int fan[MAX];

// �迭�� �ʱ�ȭ(���� 1, ���� 0, �Է¾��� -1)
int initArray(int * arr) {
	memset(arr, -1, sizeof(arr));
	string input;
	cin >> input;
	for (int i = 0; i < input.length(); i++)
		if (input.at(i) == 'M')
			arr[i] = 1;
		else if (input.at(i) == 'F')
			arr[i] = 0;
		else {
			return 0;
		}
	return input.length();
}

// �� �迭�� ���Ͽ�, ����-���ڰ� ���� ��� 1�� return
int compare(int memNum) {
	for (int i = 0; i < memNum; i++)
		if (member[i] == 1 && fan[i] == 1)
			return 0;
	return 1;
}

// �������� �� ĭ �� �ű�
void shiftFan(int fanNum) {
	for (int i = 1; i < fanNum; i++)
		fan[i - 1] = fan[i];
	fan[fanNum - 1] = -1;
}

// �� �迭�� ���� ���̸�ŭ �ݺ��Ͽ� ���ϰ� �ű�⸦ �ݺ�
int run()
{
	int memNum = initArray(member);
	int fanNum = initArray(fan);

	int result = 0;

	bool flag = false;
	for (int i = 0; i <= fanNum - memNum; i++)
	{
		result += compare(memNum);
		shiftFan(fanNum);
	}

	return result;
}

int main()
{
	int tc;
	cin >> tc;

	int num;
	while (tc > 0) {
		answer.push(run());
		tc--;
	}
	while (!answer.empty()) {
		cout << answer.front() << endl;
		answer.pop();
	}
	return 0;
}