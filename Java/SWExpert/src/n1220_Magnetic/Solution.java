package n1220_Magnetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1220_Magnetic
 * 메모리 :	17,148 kb
 * 실행시간 :	100 ms
 */

public class Solution {
	static char[][] table = new char[100][];
	static final int N = 100;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			result.append("#").append(tc).append(" ");
			// 00. 입력한다.
			br.readLine();
			for(int i = 0; i < N; i++) {
				table[i] = br.readLine().toCharArray();
			}
			// 01
			Queue<Integer> queue = 
		}
	}
}
