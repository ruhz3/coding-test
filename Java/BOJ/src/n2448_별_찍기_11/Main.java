package n2448_별_찍기_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static char[][] board;
	static int N, R, C;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 00. 입력 받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		R = N;
		C = 2*N;
		board = new char[R][C];
		for(int i = 0; i < R; i++) {
			Arrays.fill(board[i], ' ');  // !!
		}
		// 01. 프랙탈 구조가 몇 차원인지 구한다.
		int num = N/3;
		int dimension = 0;
		while(num > 0) {
			num = num >> 1;
			dimension++;
		}
		// 02. 프랙탈을 board에 기록한다.
		practal(0, C/2-1, dimension-1);
		
		// 03. 출력한다.
		print();
	}
	
	private static void practal(int r, int c, int dimension) {
		// 해당 점을 방문했다면, 가장 작은 삼각형을 출력한다. 
		stamp(r, c);
		
		// 더 들어갈 차원이 있다면, 내부에 점을 찍어준다.
		if(dimension >= 1) {
			int lr = r + 3 * (1 << dimension-1);
			int lc = c - 3 * (1 << dimension-1);
			int rr = r + 3 * (1 << dimension-1);
			int rc = c + 3 * (1 << dimension-1);
			practal(r, c, dimension - 1);
			practal(lr, lc, dimension - 1);
			practal(rr, rc, dimension - 1);
		}
	}
	private static void stamp(int r, int c) {
		// 가장 작은 삼각형을 출력하되, 이미 찍혀있다면 바로 반환한다.
		if(board[r][c] == '*') return;
		board[r][c] = '*';
		board[r+1][c-1] = '*';
		board[r+1][c+1] = '*';
		for(int i = c-2; i <= c+2; i++) board[r+2][i] = '*';
		return;
	}
	private static void print() {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++)
				result.append(board[i][j]);
			result.append("\n");
		}
		System.out.println(result.toString());
	}
}
