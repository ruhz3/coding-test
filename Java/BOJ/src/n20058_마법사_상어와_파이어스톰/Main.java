package n20058_마법사_상어와_파이어스톰;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, Q;
	static int M;
	static int[][] board;
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
	
	static Queue<int[]> queue = new LinkedList<>();
	static boolean[][] isVisited;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		// 01. 보드의 크기에 맞춰 배열을 생성한다.
		M = 1 << N;
		board = new int[M][M];
		isVisited = new boolean[M][M];
		
		// 02. 보드를 입력한다.
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 03. Storm으로 회전 시킨 후, Fire로 얼음을 녹인다.
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < Q; i++) {
			makeStorm(Integer.parseInt(st.nextToken()));
			makeFire();
		}
		
		// 04. BFS 탐색하며, 최대 영역넓이와, 총 얼음량을 구한다.
		int sum = 0;
		int maxCount = 0;
		for(int r = 0; r < M; r++) {
			for(int c = 0; c < M; c++) {
				if(isVisited[r][c] || board[r][c] == 0) continue;
				int count = 0;
				
				queue.offer(new int[] {r, c});
				isVisited[r][c] = true;
				sum += board[r][c];
				count++;
				
				// 05. 4방 탐색한다.
				while(!queue.isEmpty()) {
					int[] coord = queue.poll();
					for(int dir = 0; dir < 4; dir++) {
						int nr = coord[0] + rowDir[dir];
						int nc = coord[1] + colDir[dir];
						if(nr < 0 || nr >= M || nc < 0 || nc >= M) continue;
						if(isVisited[nr][nc] || board[nr][nc] == 0) continue;
						
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						sum += board[nr][nc];
						count++; 
					}
				}
				// 06. Queue가 비워지고 나면, 영역 넓이를 비교한다.
				maxCount = Math.max(count, maxCount);
			}
		}
		// 07. 출력한다.
		System.out.println(sum);
		System.out.println(maxCount);
	}

	private static void makeStorm(int L) {
		// 00. 기존 배열을 복사한다.
		int[][] newBoard = new int[M][];
		for(int i = 0; i < M; i++) {
			newBoard[i] = board[i].clone();
		}
		
		// 01. (i, j)를 (0, 0)으로 하여 grid만큼의 사각형 범위를 정한다.
		int grid = 1<<L;
		for(int i = 0; i < M; i += grid) {
			for(int j = 0; j < M; j += grid) {
				
				// 02. level을 증가시키며 내부의 사각형 까지 회전시킨다.
				for(int level = 0; level < grid/2; level++) {
					int r = i + level;
					int c = j + level;
					int lv = grid - 2 * level;
					
					for(int k = 0; k < lv-1; k++) {
						// 구역 1 : 상행 -> 우열 
						newBoard[r + k][c + lv-1] = board[r + 0][c + k];
						// 구역 2 : 우열 -> 하행
						newBoard[r + lv-1][c + lv-1-k] = board[r + k][c + lv-1];
						// 구역 3 : 하행 -> 좌열
						newBoard[r + lv-1-k][c + 0] = board[r + lv-1][c + lv-1-k];
						// 구역 4 : 좌열 -> 상행
						newBoard[r + 0][c + k] = board[r + lv-1-k][c + 0];
					}

				}
			}
		}
		// 03. 새롭게 생성한 배열을 static 변수에 갱신해준다.
		board = newBoard;
	}
	private static void makeFire() {
		// 00. 기존 배열을 복사한다.
		int[][] newBoard = new int[M][];
		for(int i = 0; i < M; i++) {
			newBoard[i] = board[i].clone();
		}
		
		// 01. 전체 board를 순회하며 4방으로 인접한 칸을 탐색한다.
		for(int r = 0; r < M; r++) {
			for(int c = 0; c < M; c++) {
				if(board[r][c] == 0) continue;
				int count = 0;
				
				for(int dir = 0; dir < 4; dir++) {
					int nr = r + rowDir[dir];
					int nc = c + colDir[dir];
					if(nr < 0 || nr >= M || nc < 0 || nc >= M || board[nr][nc] == 0) continue;
					count++;
				}
				if(count < 3) newBoard[r][c]--;
			}
		}
		
		// 02. 새롭게 생성한 배열을 static 변수에 갱신해준다.
		board = newBoard;
	}
}
