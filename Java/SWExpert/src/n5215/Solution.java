package n5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 5215_햄버거 다이어트
 * 메모리 :	19,748 kb
 * 실행시간 :	156 ms
 */

public class Solution {
	static int[] scores = new int[20];
	static int[] calories = new int[20];
	static int N;
	static int L;
	
	public static int findBestCombination(int idx, int nowScore, int nowCalory) {
		// 00. 현재 인덱스 이후의 모든 원소들을 하나씩 넣어본다. 
		int maxScore = nowScore;
		for(int i = idx+1; i < N; i++) {
			int score;
			
			// 01. 칼로리 기준치를 넘어가면 넣을 수 없다.
			if(nowCalory + calories[i] > L) {
				continue;
			}
			
			// 02. 각 원소를 넣었을 때의 최대 점수를 가져와, 최댓값을 찾는다.
			score = findBestCombination(i, nowScore + scores[i], nowCalory + calories[i]);  
			if(score > maxScore) {
				maxScore = score;
			}
		}
		return maxScore;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			result.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			// 00. 점수와 칼로리를 입력 받는다.
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				scores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}
			
			// 01. 찾는다.
			result.append(findBestCombination(-1, 0, 0)).append("\n");
		}
		System.out.println(result.toString());
	}
}
