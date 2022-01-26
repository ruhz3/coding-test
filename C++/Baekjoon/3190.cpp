#include <iostream>
#include <utility>
#include <queue>
using namespace std;

int N;
int appleNum;
int rotateNum;
int map[101][101];

vector<pair<int, int>> Snake;
queue<pair<int, int>> Rotate;
int currentTime = 1;
int currentDir = 2;

// 해당 시간에 방향을 전환
int changeDirection(int time, int dir) {
	if (Rotate.front().first == time) {
		dir += Rotate.front().second;
		if (dir == 5)
			dir = 1;
		if (dir == 0)
			dir = 4;
		Rotate.pop();
	}
	return dir;
}

// 뱀을 규칙에 맞게 이동
bool moveSnake() {
	int current_head_I = Snake.back().first;
	int current_head_J = Snake.back().second;

	// 다음 head의 좌표 찾기
	int next_head_I;
	int next_head_J;

	switch (currentDir) {
	case 1: // 상
		next_head_I = -1;
		next_head_J = 0;
		break;
	case 2: // 우
		next_head_I = 0;
		next_head_J = 1;
		break;
	case 3: // 하
		next_head_I = 1;
		next_head_J = 0;
		break;
	case 4: // 좌
		next_head_I = 0;
		next_head_J = -1;
		break;
	}
	next_head_I += current_head_I;
	next_head_J += current_head_J;

	// head의 벽 충돌 검사
	if (next_head_I > N || next_head_J > N || next_head_I < 1 || next_head_J < 1) {
		return false;
	}
	// head의 자기자신 충돌 검사
	for (int i = 0; i < Snake.size() - 1; i++) {
		if (Snake[i].first == next_head_I && Snake[i].second == next_head_J) {
			return false;
		}
	}
	// head를 추가해주고
	Snake.push_back(make_pair(next_head_I, next_head_J));

	// head위치에 사과가 없다면 꼬리를 삭제
	if (map[next_head_I][next_head_J] != -1) {
		Snake.erase(Snake.begin());
	}
	// head위치에 사과가 있다면 사과를 삭제
	else {
		map[next_head_I][next_head_J] = 0;
	}
	return true;
}

int main() {
	cin >> N;
	
	// 사과 위치 입력
	int getI;
	int getJ;
	cin >> appleNum;
	for (int i = 0; i < appleNum; i++) {
		cin >> getI >> getJ;
		map[getI][getJ] = -1;
	}
	
	// 회전 정보 입력
	int time;
	char Dir;
	pair<int, int> input;
	cin >> rotateNum;
	for (int i = 0; i < rotateNum; i++) {
		cin >> time >> Dir;
		input.first = time;

		// 오른쪽은 시계방향(+1), 왼쪽은 반시계방향(-1)
		if (Dir == 'L') input.second = -1;
		else if (Dir == 'D') input.second = 1;

		Rotate.push(input);
	}

	Snake.push_back(make_pair(1, 1));

	// moveSnake가 종료조건에 안 걸리면 계속 뱀 이동
	while (moveSnake()) {
		if (Rotate.size() > 0) {
			currentDir = changeDirection(currentTime, currentDir);
		}
		currentTime++;
	}

	cout << currentTime << endl;

	return 0;
}