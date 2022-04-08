package n2578_빙고;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int[5][5];
	static boolean[][] visit = new boolean[5][5];
	static int[] call = new int[25];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 00. 맵을 입력한다.
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 01. 사회자가 부를 숫자를 입력한다.
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 5; j++) {
				call[5*i+j] = Integer.parseInt(st.nextToken());
			}
		}
		// 02. 게임을 진행한다.
		int round = 0;
		while(true) {
			if(check(call[round])) break;
			round++;
		}
		System.out.println(round+1);
	}
	
	public static boolean check(int num) {
		int row = 0;
		int col = 0;
	OUTER : for(row = 0; row < 5; row++) {
			for(col = 0; col < 5; col++) {
				if(map[row][col] == num) {
					visit[row][col] = true;
					break OUTER;
				}
			}
		}
		int bingo = 0;
		for(int i = 0; i < 5; i++) {
			if(visit[i][0] && visit[i][1] && visit[i][2] && visit[i][3] && visit[i][4]) bingo++;
			if(visit[0][i] && visit[1][i] && visit[2][i] && visit[3][i] && visit[4][i]) bingo++;
		}
		if(visit[0][0] && visit[1][1] && visit[2][2] && visit[3][3] && visit[4][4]) bingo++;
		if(visit[0][4] && visit[1][3] && visit[2][2] && visit[3][1] && visit[4][0]) bingo++;
		return (bingo >= 3);
	}
}
