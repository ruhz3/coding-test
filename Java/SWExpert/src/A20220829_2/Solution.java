package A20220829_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {
	static ArrayList<int[]> list = new ArrayList<>();
	static int[][] board;
	// 상, 우, 하, 좌
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
	static int N;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 1
							&& i != 0 && j != 0
							&& i != N-1 && j != N-1) list.add(new int[] {i, j});
				}
			}
			sb.append('#').append(tc).append(' ').append(dfs(0)).append('\n');
		}
		System.out.println(sb.toString());
	}
	private static int dfs(int index) {
		if(index >= list.size()) return 0;
		// 00. 좌표를 리스트에서 가져온다.
		int[] coord = list.get(index);
		int r = coord[0];
		int c = coord[1];
		
		int minSum = Integer.MAX_VALUE;
		for(int dir = 0; dir < 4; dir++) {
			// 01. 현재 좌표 기준 방향에 맞게 그려준다.
			int paintCount= paint(r, c, dir, false);
			if(paintCount == -1) {
				paint(r, c, dir, true);
				continue;
			}
			System.out.println(dir);
			print();
			minSum = Math.min(minSum, dfs(index+1) + paintCount);
			paint(r, c, dir, true);
		}
		return minSum;
	}
	private static int paint(int r, int c, int dir, boolean isReverse) {
		// 상, 하, 좌, 우
		int count = 0;
		if(dir == 0) {
			for(int i = 0; i < r; i++) {
				if(!isReverse && board[i][c] != 0) return -1;
				board[i][c] = isReverse ? 0 : -1;
				count++;
			}
		} else if(dir == 1) {
			for(int i = r+1; i < N; i++) {
				if(!isReverse && board[i][c] != 0) return -1;
				board[i][c] = isReverse ? 0 : -1;
				count++;
			}
		} else if(dir == 2) {
			for(int j = 0; j < c; j++) {
				if(!isReverse && board[r][j] != 0) return -1;
				board[r][j] = isReverse ? 0 : -1;
				count++;
			}
		} else if(dir == 3) {
			for(int j = c+1; j < N; j++) {
				if(!isReverse && board[r][j] != 0) return -1;
				board[r][j] = isReverse ? 0 : -1;
				count++;
			}
		}
		return count;
	}
	private static void print() {
		for(int i = 0; i < N; i++) {
			for(int j= 0; j < N; j++) {
				System.out.printf("%2d", board[i][j]);
			} System.out.println();
		}
	}
}
