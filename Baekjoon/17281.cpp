#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

int N;
int hitTable[50][9];
int entry[9];
vector<int> field;

int findMaxScore() {
	int outCount;
	int entryIdx = 0;
	int score = 0;

	for (int inning = 0; inning < N; inning++) {
		// 이닝이 시작되면, outCount와 필드 초기화
		outCount = 0;
		field.clear();
		while (outCount < 3) {
			int hitScore = hitTable[inning][entry[entryIdx]];
			// 현재 선수 아웃을 친 경우
			if (hitScore == 0) {
				outCount++;
			}
			// 현재 선수 안타를 친 경우
			else {
				// 선수 한 명을 넣어주고, 필드 위의 모든 선수 진루
				field.push_back(0);
				for (int i = 0; i < field.size(); i++) {
					field[i] += hitScore;
				}
				// 필드 위에 4이상인 선수 빼주면서 점수++
				while(!field.empty() && field[0] >= 4) {
					field.erase(field.begin());
					score++;
				}
			}
			// 다음 선수 입장
			if (entryIdx == 8) entryIdx = 0;
			else entryIdx++;
		}
	}
	return score;
}

int makeEntry(int entryNum){
	// entry가 완성되면 findMaxScore() 호출
	if (entryNum == 9) {
		return findMaxScore();
	}
	// 3번타자는 0번선수 고정
	if (entryNum == 3) {
		entry[3] = 0;
		return makeEntry(entryNum + 1);
	}
	
	int res = 0;
	bool flag;
	for (int i = 1; i < 9; i++) {
		// 0번 선수 제외, 이전 entry에 넣으려는 선수가 있는지 검사해 flag 저장
		flag = true;
		for (int j = 0; j < entryNum; j++) {
			if (i == entry[j])
				flag = false;
		}
		// 없다면 선수를 해당 entry에 넣고 다음 entry 작성
		if (flag) {
			entry[entryNum] = i;
			res = max(res, makeEntry(entryNum + 1));
		}
	}
	return res;
}

int main() {
	memset(entry, -1, sizeof(entry));
	cin >> N;
	for (int inning = 0; inning < N; inning++)
		for (int player = 0; player < 9; player++)
			cin >> hitTable[inning][player];

	cout << makeEntry(0) << endl;
	return 0;
}