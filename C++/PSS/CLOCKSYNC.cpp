#include <iostream>

using namespace std;

int clock[16];
int button[10][5] = {
	{0, 1, 2, -1, -1},
	{3, 7, 9, 11, -1},
	{4, 10, 14, 15, -1},
	{0, 4, 5, 6, 7},
	{6, 7, 8, 10, 12},
	{0, 2, 14, 15, -1},
	{3, 14, 15, -1, -1},
	{4, 5, 7, 14, 15},
	{1, 2, 3, 4, 5},
	{3, 4, 5, 9, 13}
};
int level = 0;
int maxLevel = 100;

// debug 도구
void printClock()
{
	puts("");
	for (int i = 0; i < 16; i++)
	{
		printf("%3d", clock[i]);
		if (i % 4 == 3)
			puts("");
	}
	puts("");
}

// 버튼을 누르고 clock의 원소를 변경하는 도구
void pressButton(int n, int a)
{
	// n은 버튼 번호, a는 +3 또는 -3으로, 배열에 더할 숫자
	if (a == 0)
		return;

	for (int i = 0; i < 5; i++)
	{
		if (button[n][i] == -1)
			break;
		else
		{   
			clock[button[n][i]] += a;
			// 0, -3, -6 등의 경우 보정
			if (clock[button[n][i]] % 12 < 3)
			{
				clock[button[n][i]] %= 12;
				clock[button[n][i]] = 12 + clock[button[n][i]];
			}
			// 15, 18, 21 등의 경우 보정
			else
				clock[button[n][i]] %= 12;
		}
	}
}

// 실제 동작을 하는 재귀함수
void run(int n)
{
	/* 
	 * clock의 모든 원소가 12가 되면 level을 maxLevel과 비교 후 반환
	 * 버튼 번호가 9가 넘어가도 반환
	 */
	bool flag = false;
	for (int i = 0; i < 16; i++)
		if (clock[i] != 12)
			flag = true;
	if (!flag)
	{
		if (level < maxLevel)
			maxLevel = level;
		cout << "Changed!" << endl;
		return;
	}
	if (n > 9)
		return;

	/*
	 * 각 버튼당 0번~3번씩 눌러보고, 누른만큼 level에 더해준다.
	 * 다음 버튼을 run 해준다.
	 * 실패했다면 run()이하 코드로 원상복구되며, 다음 회차 시도
	 */
	for (int i = 0; i < 4; i++)
	{
		pressButton(n, 3*i);
		level += i;
		run(n + 1);
		pressButton(n, -3*i);
		level -= i;
	}

	return;
}


int main()
{
	// 입력받고, run() 호출, maxLevel 출력 후 종료
	cout << "Input>> ";
	for (int i = 0; i < 16; i++)
			cin >> clock[i];

	printClock(); // debug
	run(0);
	
	if (maxLevel == 100)
		maxLevel = -1;

	cout << "Result : " << maxLevel << endl;

	return 0;
}