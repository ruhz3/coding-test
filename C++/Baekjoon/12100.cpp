#include<iostream>   
#include<algorithm>
#include<queue>
using namespace std;

int board[20][20] = { 0 };

int N;
int maxNum = 0;

// 최대값 찾는 함수
int CalMax() {
	int res = 0;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			res = max(res, board[i][j]);
	return res;
}

// 움직이는 함수
void Move(int dir) {
	queue<int> Qcal;
	int prev;
	int idx;
	// 1, 2, 3, 4 = 상, 하, 좌, 우
	switch (dir) {
	case 1:
		for (int C = 0; C < N; C++) {
			idx = 0;
			// 큐에 열의 문자들을 push
			for (int R = 0; R < N; R++) {
				if (board[R][C] != 0) {
					Qcal.push(board[R][C]);
					board[R][C] = 0;
				}
			}
			// 큐에 아직 숫자가 남았다면
			while (!Qcal.empty()) {
				// 1. 맨 앞 숫자를 하나 꺼낸다
				prev = Qcal.front();
				Qcal.pop();
				// 2. 큐가 비거나 그 뒷 숫자와 다르면 꺼낸 숫자를 그대로 넣는다.
				if (Qcal.empty() || prev != Qcal.front()) {
					board[idx++][C] = prev;
				}
				// 3. 뒷 숫자가 똑같다면 그것도 꺼내고 합한 값을 넣는다.
				if (!Qcal.empty() && prev == Qcal.front()) {
					prev *= 2;
					Qcal.pop();
					board[idx++][C] = prev;
				}
			}
		}
		break;

	case 2:
		for (int C = 0; C < N; C++) {
			idx = N - 1;
			for (int R = N - 1; R >= 0; R--) {
				if (board[R][C] != 0) {
					Qcal.push(board[R][C]);
					board[R][C] = 0;
				}
			}
			while (!Qcal.empty()) {
				prev = Qcal.front();
				Qcal.pop();
				if (Qcal.empty() || prev != Qcal.front()) {
					board[idx--][C] = prev;
				}
				if (!Qcal.empty() && prev == Qcal.front()) {
					prev *= 2;
					Qcal.pop();
					board[idx--][C] = prev;
				}
			}
		}
		break;

	case 3:
		for (int R = 0; R < N; R++) {
			idx = 0;
			for (int C = 0; C < N; C++) {
				if (board[R][C] != 0) {
					Qcal.push(board[R][C]);
					board[R][C] = 0;
				}
			}
			while (!Qcal.empty()) {
				prev = Qcal.front();
				Qcal.pop();
				if (Qcal.empty() || prev != Qcal.front()) {
					board[R][idx++] = prev;
				}
				if (!Qcal.empty() && prev == Qcal.front()) {
					prev *= 2;
					Qcal.pop();
					board[R][idx++] = prev;
				}
			}
		}
		break;

	case 4:
		for (int R = 0; R < N; R++) {
			idx = N - 1;
			for (int C = N - 1; C >= 0; C--) {
				if (board[R][C] != 0) {
					Qcal.push(board[R][C]);
					board[R][C] = 0;
				}
			}
			while (!Qcal.empty()) {
				prev = Qcal.front();
				Qcal.pop();
				if (Qcal.empty() || prev != Qcal.front()) {
					board[R][idx--] = prev;
				}
				if (!Qcal.empty() && prev == Qcal.front()) {
					prev *= 2;
					Qcal.pop();
					board[R][idx--] = prev;
				}
			}
		}
		break;
	}
}

// 완전 탐색하는 함수
void Find(int lev){
	// 탈출 조건
	if (lev == 5) {
		maxNum = max(maxNum, CalMax());
		return;
	}

	// 미리 현재 상태를 저장
	int tmp_board[20][20];
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++)
			tmp_board[i][j] = board[i][j];
	}
	
	for (int i = 1; i <= 4; i++) {
		// 옮기고 다음 재귀함수 호출
		Move(i);
		Find(lev + 1);
		// 저장해둔 배열 다시 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				board[i][j] = tmp_board[i][j];
		}
	}
}

int main(){
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++)
			cin >> board[i][j];
	}
	Find(0);
	cout << maxNum << endl;
	return 0;
}