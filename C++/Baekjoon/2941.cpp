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
	
	// ���ۿ� ù ���ڸ� ��´�.
	for (int i = 0; i < len; i++) {
		buf.clear();
		buf.push_back(word[i]);

		// ���� ���� �ڿ� 2������ ���ۿ� ��� �˻��Ѵ�.
		for (int j = 1; j < 3; j++) {
			if ((i + j) >= len)
				break;
			buf.push_back(word[i+j]);

			// ã�� �ܾ ������ �ݿ��ϰ�, ���� �˻��� ù���ڸ� ã�� �ܾ� �ڷ� ���ش�.
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