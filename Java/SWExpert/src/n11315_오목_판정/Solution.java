package n11315_오목_판정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 11315_오목 판정
 * 메모리 :	17,148 kb
 * 실행시간 :	100 ms
 */

public class Solution {
	static char[][] board;
	static int[] rowDir = {0, 1, -1, 1}; // 우, 하, 우상, 우하
	static int[] colDir = {1, 0, 1, 1}; // 우, 하, 우상, 우하
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			result.append("#").append(tc).append(" ");
			// 00. 입력한다.
			N  = Integer.parseInt(br.readLine());
			board = new char[N][];
			for(int i = 0; i < N; i++) {
				board[i] = br.readLine().toCharArray();
			}
			// 01. 4가지 방향 중 하나라도 check이 된다면, YES
			if(rowCheck() || colCheck() || downCrossCheck() || upCrossCheck()) result.append("YES").append("\n");
			else result.append("NO").append("\n");
		}
		System.out.println(result.toString());
	}
	
	private static boolean rowCheck() {
		for(int i = 0; i < N; i++) {
			if(check(i, 0, 0)) return true;
		}
		return false;
	}
	private static boolean colCheck() {
		for(int i = 0; i < N; i++) {
			if(check(0, i, 1)) return true;
		}
		return false;
	}
	private static boolean upCrossCheck() {
		for(int i = 4; i < N; i++) {
			if(check(i, 0, 2)) return true;
		}
		for(int j = 1; j < N-4; j++) {
			if(check(N-1, j, 2)) return true;
		}
		return false;
	}
	private static boolean downCrossCheck() {
		for(int i = 0; i < N-4; i++) {
			if(check(i, 0, 3)) return true;
		}
		for(int j = 1; j < N-4; j++) {
			if(check(0, j, 3)) return true;
		}
		return false;
	}
	
	private static boolean check(int sr, int sc, int dir) {
		int nr = sr;
		int nc = sc;
		int count = 0;
		while(true) {			
			if(board[nr][nc] == 'o') {
				if(++count == 5) return true;
			}
			else count = 0;
			nr += rowDir[dir];
			nc += colDir[dir];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
		}
		return false;
	}

}
