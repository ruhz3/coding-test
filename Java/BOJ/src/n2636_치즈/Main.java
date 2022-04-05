package n2636_치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Coord {
	int r, c;

	public Coord(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static Queue<Coord> queue = new LinkedList<Coord>();
	static boolean[][] isVisited;
	static int[] rowDir = {-1, 0, 1, 0};  // 상, 우, 하, 좌
	static int[] colDir = {0, 1, 0, -1};  // 상, 우, 하, 좌
	
	static int[][] board;
	static int R, C;
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		isVisited = new boolean[R][C];
		
		int time = 1;
		int cheeseCount = 0;
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) cheeseCount++;
			}
		}
		
		// 01. 치즈가 모두 녹을 때 까지 다음을 반복한다.
		int prevCheeseCount = 0;
		while(true) {
			// 01-00. 외부 공기가 닿는 부분을 BFS로 탐색하여 -1 표시한다.
			queue.clear();
			for(int i = 0; i < R; i++) {
				Arrays.fill(isVisited[i], false);
			}
			isVisited[0][0] = true;
			queue.add(new Coord(0, 0));
			
			while(!queue.isEmpty()) {
				Coord c = queue.poll();
				board[c.r][c.c] = -1;
				
				for(int dir = 0; dir < 4; dir++) {
					int nr = c.r + rowDir[dir];
					int nc = c.c + colDir[dir];
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					if(isVisited[nr][nc] || board[nr][nc] == 1) continue;
					
					isVisited[nr][nc] = true;
					queue.offer(new Coord(nr, nc));
				}
			}
			
			// 01-01. 외부 공기(-1)가 닿으면 0으로 바꾸고, 남은 치즈 개수를 빼준다. 
			prevCheeseCount = cheeseCount;
			
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(board[i][j] != 1) continue;
					
					for(int dir = 0; dir < 4; dir++) {
						int nr = i + rowDir[dir];
						int nc = j + colDir[dir];
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
						if(board[nr][nc] == -1) {
							board[i][j] = 0;
							cheeseCount--;
							break;
						}
					}
				}
			}
			// 01-02. 녹일 치즈가 하나도 없었다면 종료한다. 
			if(cheeseCount == 0) break;
			time++;
		}
		
		// 02. 출력한다.
		System.out.println(new StringBuilder().append(time).append("\n").append(prevCheeseCount));
	}
}
