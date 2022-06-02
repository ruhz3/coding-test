package n14500_테트로미노;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board;
	static boolean[][] isVisited;
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		isVisited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) board[i][j] = Integer.parseInt(st.nextToken());
		}
		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				isVisited[i][j] = true;
				result = Math.max(result, normalBlock(i, j, 1, board[i][j]));
				result = Math.max(result, specialBlock(i, j));
				isVisited[i][j] = false;
			}
		}
		System.out.println(result);
	}
	private static int normalBlock(int r, int c, int cnt, int score) {
		if(cnt == 4) return score;
		int maxScore = 0;
		for(int dir = 0; dir < 4; dir++) {
			int nr = r + rowDir[dir];
			int nc = c + colDir[dir];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || isVisited[nr][nc]) continue;
			isVisited[nr][nc] = true;
			maxScore = Math.max(maxScore, normalBlock(nr, nc, cnt+1, score + board[nr][nc]));
			isVisited[nr][nc] = false;
		}
		return maxScore;
	}
	private static int specialBlock(int r, int c) {
		int maxScore = 0;
		for(int blockedDir = 0; blockedDir < 4; blockedDir++) {
			int scoreSum = board[r][c];
			for(int dir = 0; dir < 4; dir++) {
				if(dir == blockedDir) continue;
				int nr = r + rowDir[dir];
				int nc = c + colDir[dir];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
					scoreSum = 0;
					break;
				}
				scoreSum += board[nr][nc];
			}
			maxScore = Math.max(maxScore, scoreSum);
		}
		return maxScore;
	}
}
