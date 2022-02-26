package n9204_체스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] rowDir = { -1, -1, 1, 1 };
	static int[] colDir = { -1, 1, -1, 1 };
	static int[][] board = new int[8][8];
	static int count = 0;
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder ret = new StringBuilder();
			for(int i = 0; i < 8; i++) {
				Arrays.fill(board[i], 0);
			}
			st = new StringTokenizer(br.readLine(), " ");
			int sr = st.nextToken().charAt(0) - 'A';
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = st.nextToken().charAt(0) - 'A';
			int ec = Integer.parseInt(st.nextToken()) - 1;
			board[sr][sc] = -1;
			board[er][ec] = -1;
			
			// 00. 먼저 색상을 칠한다.
			paint(sr, sc);
			
			// 01. 다음 색상을 칠한다.
			String get = paint(er, ec);
			if(get.equals("")) {
				ret.append("Impossible").append("\n");
			} else if(count == 0) {
				ret.append(0).append(" ").append((char)(sr+'A')).append(" ").append(sc+1).append("\n");
			} else {
				ret.append(0).append(" ").append((char)(sr+'A')).append(" ").append(sc+1).append(" ");
				result.append((char)(er+'A')).append(" ").append(ec+1);
				System.out.println(count + " " + result.toString());
			}
		}
	}

	private static String paint(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r;
			int nc = c;

			while (true) {
				nr += rowDir[i];
				nc += colDir[i];
				if (nr < 0 || nr >= 8 || nc < 0 || nc >= 8)
					break;

				if (board[nr][nc] == -1) {
					return "";
				}
				else if (board[nr][nc] == 1) {
					count++;
					return Integer.toString(nr) + " " + Integer.toString(nc);
				}
				else if(board[nr][nc] == 0) {
					board[nr][nc] = 1;
				}
			}
		}
		return "Impossible";
	}
}
