#include <iostream>
#include <cstring>
#include <algorithm>
#include <queue>
using namespace std;
queue<int> answer;

int map[20000];

int run(int num) {
	// 배열 초기화
	memset(map, 0, sizeof(map));
	int * tmp = new int[num];
	for (int i = 0; i < num; i++) {
		cin >> map[i];
		tmp[i] = map[i];
	}
	sort(tmp, tmp + num);
	
	int height, width, count;
	int area = 0;

	for (int i = 0; i < num; i++) {
		// 작업할 높이 (height) 설정
		if (i > 0 && tmp[i] == tmp[i - 1])
			continue;
		height = tmp[i];
		
		// 작업할 높이에 해당하는 최대 너비 (width) 설정
		width = 1; count = 1;
		for (int j = 1; j < num; j++)
			if (map[j - 1] >= height && map[j] >= height) {
				count++;
				if (j == num - 1 && count > width)
					width = count;
			}
			else {	
				if (count > width)
					width = count;
				count = 1;
			}

		// 높이 X 너비가 면적보다 크다면 area에 저장
		if (height * width > area)
			area = height * width;
	}
	
	return area;
}

int main() {
	int tc;
	cin >> tc;

	int num;
	while (tc > 0) {
		cin >> num;
		answer.push(run(num));
		tc--;
	}
	while (!answer.empty()) {
		cout << answer.front() << endl;
		answer.pop();
	}
	return 0;
}