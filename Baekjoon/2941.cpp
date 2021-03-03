#include <iostream>
#include <string>
#include <cstring>
using namespace std;

string word;
static string croats[8] = {
	"c=", "c-", "dz=", "d-",
	"lj", "nj", "s=", "z=" };


int count() {
	int len = word.size();
	int cnt = len;
	string buf;
	
	// 버퍼에 첫 글자를 담는다.
	for (int i = 0; i < len; i++) {
		buf.clear();
		buf.push_back(word[i]);

		// 담은 글자 뒤에 2개까지 버퍼에 담아 검사한다.
		for (int j = 1; j < 3; j++) {
			if ((i + j) >= len)
				break;
			buf.push_back(word[i+j]);

			// 찾은 단어를 갯수에 반영하고, 다음 검사할 첫글자를 찾은 단어 뒤로 해준다.
			for (int k = 0; k < 8; k++) {
				if (buf.compare(croats[k]) == 0) {
					cnt -= (croats[k].size() - 1);
					i += (croats[k].size() - 1);
					break;
				}
			}
		}
	}
	return cnt;
}


int main() {
	cin >> word;
	cout << count() << endl;

	return 0;
}