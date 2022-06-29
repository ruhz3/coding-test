package weekly_아이템_줍기;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[][] rectangle = { { 1, 1, 3, 7 }, { 2, 2, 7, 4 }, { 4, 3, 6, 6 } };
	static int characterX = 1;
	static int characterY = 2;
	static int itemX = 6;
	static int itemY = 6;

	public static void main(String[] args) {
		int res = new Solution().solution(rectangle, characterX, characterY, itemX, itemY);
		System.out.println(res);
	}
}

class Solution {
	static boolean[][] origin = new boolean[102][102];
	static boolean[][] map;
	static int N, M;

	static Queue<int[]> queue = new LinkedList<>();
	static boolean[][] isVisited;
	static int[] rowDir = { -1, 0, 1, 0, -1, 1, 1, -1 };
	static int[] colDir = { 0, 1, 0, -1, 1, 1, -1, -1 };

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		int maxRow = 0;
		int maxCol = 0;
		// 00. 사각형 정보를 origin 배열에 입력한다.
		for (int[] coords : rectangle) {
			int sr = coords[0] * 2;
			int sc = coords[1] * 2;
			int er = coords[2] * 2;
			int ec = coords[3] * 2;
			maxRow = Math.max(maxRow, er);
			maxCol = Math.max(maxCol, ec);
			for (int r = sr; r <= er; r++)
				for (int c = sc; c <= ec; c++)
					origin[r][c] = true;
		}
		// 01. map에는 지나다닐 수 있는 길만 표시한다.
		N = maxRow + 2;
		M = maxCol + 2;
		map = new boolean[N][M];
		isVisited = new boolean[N][M];
		for (int i = 1; i < N; i++)
			for (int j = 1; j < M; j++)
				if (check(i, j))
					map[i][j] = true;

		// 02. map을 따라 BFS 탐색한다.
		queue.offer(new int[] { characterX * 2, characterY * 2 });
		int count = 0;
		OUTER: while (!queue.isEmpty()) {
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				int[] coord = queue.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nr = coord[0] + rowDir[dir];
					int nc = coord[1] + colDir[dir];
					if (nr == itemX * 2 && nc == itemY * 2) break OUTER;
					if (map[nr][nc] && !isVisited[nr][nc]) {
						isVisited[nr][nc] = true;
						queue.offer(new int[] { nr, nc });
					}
				}
			}
			count++;
		}
		return count / 2 + 1;
	}
	
	/* '테두리'에 해당하는지 확인하는 함수 */
	private boolean check(int r, int c) {
		if (!origin[r][c])
			return false;
		for (int dir = 0; dir < 8; dir++) {
			int nr = r + rowDir[dir];
			int nc = c + colDir[dir];
			if (!origin[nr][nc])
				return true;
		}
		return false;
	}
}