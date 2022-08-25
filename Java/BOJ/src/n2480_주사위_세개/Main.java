package n2480_주사위_세개;
// 11504KB, 76ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 00. 배열에 개수를 카운트하며 maxCount를 찾는다.
		int maxCount = 0;
		int[] count = new int[7];
		for(int i = 0; i < 3; i++) {
			int n = Integer.parseInt(st.nextToken());
			maxCount = Math.max(maxCount, ++count[n]); 
		}
		// 01. keyNum을 카운트, 숫자 크기 순으로 찾는다.
		int keyNum = 0;
		for(int i = 6; i >= 1; i--) {
			if(count[i] == maxCount) {
				keyNum = i;
				break;
			}
		}
		// 02. maxCount와 keyNum으로 결과를 출력한다. 
		int result = 0;
		if(maxCount == 1) {
			result = keyNum * 100;
		} else if(maxCount == 2) {
			result = 1000 + keyNum * 100;
		} else {
			result = 10000 + keyNum * 1000;
		}
		System.out.println(result);
	}
}
