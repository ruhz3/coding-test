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

// debug ����
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

// ��ư�� ������ clock�� ���Ҹ� �����ϴ� ����
void pressButton(int n, int a)
{
	// n�� ��ư ��ȣ, a�� +3 �Ǵ� -3����, �迭�� ���� ����
	if (a == 0)
		return;

	for (int i = 0; i < 5; i++)
	{
		if (button[n][i] == -1)
			break;
		else
		{   
			clock[button[n][i]] += a;
			// 0, -3, -6 ���� ��� ����
			if (clock[button[n][i]] % 12 < 3)
			{
				clock[button[n][i]] %= 12;
				clock[button[n][i]] = 12 + clock[button[n][i]];
			}
			// 15, 18, 21 ���� ��� ����
			else
				clock[button[n][i]] %= 12;
		}
	}
}

// ���� ������ �ϴ� ����Լ�
void run(int n)
{
	/* 
	 * clock�� ��� ���Ұ� 12�� �Ǹ� level�� maxLevel�� �� �� ��ȯ
	 * ��ư ��ȣ�� 9�� �Ѿ�� ��ȯ
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
	 * �� ��ư�� 0��~3���� ��������, ������ŭ level�� �����ش�.
	 * ���� ��ư�� run ���ش�.
	 * �����ߴٸ� run()���� �ڵ�� ���󺹱��Ǹ�, ���� ȸ�� �õ�
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
	// �Է¹ް�, run() ȣ��, maxLevel ��� �� ����
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