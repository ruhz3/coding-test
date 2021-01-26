#include <iostream>
#include <string>

using namespace std;
/*
int n;
int S[100];
int cache[101];

// debug
void printCache()
{
	puts("");
	for (int i = 0; i < n; i++)
		cout << cache[i] << " ";
	puts("");
}

int LIS(int idx)
{
	puts("");
	cout << idx << endl;	//debug
	printCache();	// debug
	int & result = cache[idx + 1];
	if (result != -1)
	{
		puts("");
		cout << "result != -1" << endl;	// debug
		return result;
	}

	result = 1;
	int tmp;
	for (int i = idx + 1; i < n; i++)
		if (idx == -1 || S[idx] < S[i])
		{
			tmp = LIS(i) + 1;
			if (result < tmp)
				result = tmp;
		}
	puts("");
	cout << "Return result : " << result << endl;	// debug
	return result;
}

int main()
{

	for (int i = 0; i < 101; i++)
		cache[i]= -1;
	cout << ">>" << endl;
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> S[i];

	cout << LIS(-1) - 1 << endl;

	return 0;
}

*/
int m, n;
int A[100], B[100];
int cache[101][101]; 

// debug
void printCache()
{
	puts("");
	for (int i = 0; i < m; i++)
	{
		for (int j = 0; j < n; j++)
			cout << cache[i][j] << " ";
		puts("");
	}
	puts("");
}

int JLIS(int A_idx, int B_idx, int num)
{
	printCache();	// debug
	int & result = cache[A_idx + 1][B_idx + 1];

	if (result != -1)
		return result;

	result = 2;
	int tmp;

	for (int i = A_idx + 1; i < m; i++)
		if ((A_idx == -1 && B_idx == -1) || num < A[i])
		{
			tmp = JLIS(i, B_idx, A[i]) + 1;
			if (result < tmp)
				result = tmp;
		}

	for (int i = B_idx + 1; i < n; i++)
		if ((A_idx == -1 && B_idx == -1) || num < B[i])
		{
			tmp = JLIS(i, A_idx, B[i]) + 1;
			if (result < tmp)
				result = tmp;
		}
		
	return result;
}

int main()
{
	for (int i = 0; i < 101; i++)
		for (int j = 0; j < 101; j++)
			cache[i][j] = -1;
	cout << ">>" << endl;
	cin >> m >> n;

	for (int i = 0; i < m; i++)
		cin >> A[i];

	for (int j = 0; j < n; j++)
		cin >> B[j];

	cout << JLIS(-1, -1, 0) - 2 << endl;

	return 0;
}
*/