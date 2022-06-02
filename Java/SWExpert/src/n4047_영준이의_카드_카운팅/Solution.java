package n4047_영준이의_카드_카운팅;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 4047_영준이의 카드 카운팅
 * 메모리 :	18,920 kb
 * 실행시간 :	100 ms
 */

public class Solution {
	static boolean[][] check = new boolean[4][14];
	static int[] count = new int[4]; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			result.append("#").append(tc).append(" ");
			for(int i = 0; i < 4; i++) {
				Arrays.fill(check[i], false);
			}Arrays.fill(count, 13);
			
			String input = br.readLine();
			int len = input.length();
			int index = 0;
			
			boolean isError = false;
			while(index < len) {
				char key = input.charAt(index++);
				int num1 = input.charAt(index++) - '0';
				int num2 = input.charAt(index++) - '0';
				if(!count(key, 10*num1 + num2)) {
					isError = true;
					break;
				}
			}
			if(isError) {
				result.append("ERROR").append("\n");	
			} else {
				for(int i = 0; i < 4; i++) {
					result.append(count[i]).append(" ");
				} result.append("\n");
			}
		}
		System.out.println(result.toString());
	}
	static private boolean count(char key, int num) {
		int index = -1;
		switch(key) {
		case 'S':
			index = 0;
			break;
		case 'D':
			index = 1;
			break;
		case 'H':
			index = 2;
			break;
		case 'C':
			index = 3;
			break;
		}
		if(!check[index][num]) {
			count[index]--;
			check[index][num] = true;
			return true;
		} else {
			return false;
		}
	}
}
