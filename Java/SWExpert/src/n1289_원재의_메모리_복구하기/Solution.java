package n1289_원재의_메모리_복구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1289_원재의 메모리 복구
 * 메모리 :	16,080 kb
 * 실행시간 :	99 ms
 */

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			// 00. 가장 첫 문자는 0과 비교한다.
			char previous = '0';
			int cnt = 0;
			String input = br.readLine();
			for(int i = 0; i < input.length(); i++) {
				// 01. 값이 변화하는 순간 count한다.
				if(input.charAt(i) != previous) {
					cnt++;
					previous = input.charAt(i);
				}
			}
			sb.append("#" + tc + " " + cnt + "\n");
		}
		// 02. 출력한다.
		System.out.print(sb.toString());
	}
}
