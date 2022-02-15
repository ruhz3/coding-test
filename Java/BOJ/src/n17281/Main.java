package n17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int hitTable[][];
	static int[] entry = {1, 2, 3, 4, 5, 6, 7, 8};
	static int[] finalEntry;
	static boolean[] field;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		hitTable = new int[N][9];
		finalEntry = new int[9];
		field = new boolean[4];
		
		for(int inn = 0; inn < N; inn++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int player = 0; player < 9; player++) {
				hitTable[inn][player] = Integer.parseInt(st.nextToken());
			}
		}
		int maxScore = 0;
		do {
			for(int i = 0, idx = 0; i < 8; i++, idx++) {
				if(idx == 3) idx++;
				finalEntry[idx] = entry[i];
			}
			maxScore = Math.max(maxScore, play());
		} while(nextPermutation());
		System.out.println(maxScore);
	}
	
	private static int play() {
		int entryIdx = 0;
		int score = 0;
		
		for (int inn = 0; inn < N; inn++) {
			// 00. 이닝 시작 시 필드 초기화한다.
			int out = 0;
			for(int i = 0; i < 4; i++) {
				field[i] = false;
			}
			// 01. 3 아웃이 나올 때 까지 게임을 진행한다.
			while (out < 3) {
				int player = finalEntry[entryIdx % 9];
				int hit = hitTable[inn][player];
				if (hit == 0) {
					out++;
				}
				else {
					// 02. 선수가 홈에 올라왔다.
					field[0] = true;
					
					// 03. 홈, 1루, 2루, 3루를 hit만큼 진루시킨다.
					for(int i = 3; i >= 0; i--) {
						if(field[i]) {
							if(i + hit >= 4) score++;
							else field[i+hit] = true;
							field[i] = false;
						}
					}
				}
				entryIdx++;
			}
		}
		return score;
	}
	/* next permutation */
	private static boolean nextPermutation() {
		// 00. 뒤에서 시작했을 때 오름차순이 끝나는 값을 찾아준다.
		int i = 7;
		while(i > 0 && entry[i - 1] >= entry[i]) --i;
		if(i == 0) return false;
		
		// 01. 위에서 찾은 값 바로 앞 수와 바꿀 수를 결정한다.
		int j = 7;
		while(entry[i - 1] >= entry[j]) --j;
		
		// 02. 바꿔준다.
		int tmp = entry[i - 1];
		entry[i - 1] = entry[j];
		entry[j] = tmp;
		
		// 03. 뒷 부분을 오름차순으로 정렬해준다.
		int k = 7;
		while(i < k) {
			tmp = entry[i];
			entry[i++] = entry[k];
			entry[k--] = tmp;
		}
		return true;
	}
}
