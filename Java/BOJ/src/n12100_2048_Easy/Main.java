package n12100_2048_Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] board;
	static int[][] spare;
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		spare = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(find(0));
	}

	private static int find(int index) {
		if (index == 5)
			return getMax();
		for (int i = 0; i < N; i++)
			spare[i] = board[i].clone();
		int res = 0;
		for (int i = 0; i < 4; i++) {
			move(i);
			res = Math.max(res, find(index + 1));
			for (int j = 0; j < N; j++)
				board[j] = spare[j].clone();
		}
		return res;
	}

	private static void move(int dir) {
		queue.clear();
		if (dir == 0) {
			for (int C = 0; C < N; C++) {
				int index = 0;
				for (int R = 0; R < N; R++) {
					if (board[R][C] != 0) {
						queue.offer(board[R][C]);
						board[R][C] = 0;
					}
				}

				while (!queue.isEmpty()) {
					int prev = queue.peek();
					queue.poll();
					if (queue.isEmpty() || prev != queue.peek()) {
						board[index++][C] = prev;
					}
					if (!queue.isEmpty() && prev == queue.peek()) {
						prev *= 2;
						queue.poll();
						board[index++][C] = prev;
					}
				}
			}
			return;
		}
		
		if (dir == 1) {
			for (int C = 0; C < N; C++) {
				int index = N - 1;
				for (int R = N - 1; R >= 0; R--) {
					if (board[R][C] != 0) {
						queue.offer(board[R][C]);
						board[R][C] = 0;
					}
				}
				while (!queue.isEmpty()) {
					int prev = queue.peek();
					queue.poll();
					if (queue.isEmpty() || prev != queue.peek()) {
						board[index--][C] = prev;
					}
					if (!queue.isEmpty() && prev == queue.peek()) {
						prev *= 2;
						queue.poll();
						board[index--][C] = prev;
					}
				}
			}
			return;
		}
		
		if (dir == 2) {
			for (int R = 0; R < N; R++) {
				int index = 0;
				for (int C = 0; C < N; C++) {
					if (board[R][C] != 0) {
						queue.offer(board[R][C]);
						board[R][C] = 0;
					}
				}
				while (!queue.isEmpty()) {
					int prev = queue.peek();
					queue.poll();
					if (queue.isEmpty() || prev != queue.peek()) {
						board[R][index++] = prev;
					}
					if (!queue.isEmpty() && prev == queue.peek()) {
						prev *= 2;
						queue.poll();
						board[R][index++] = prev;
					}
				}
			}
			return;
			
		}
		if (dir == 3) {
			for (int R = 0; R < N; R++) {
				int index = N - 1;
				for (int C = N - 1; C >= 0; C--) {
					if (board[R][C] != 0) {
						queue.offer(board[R][C]);
						board[R][C] = 0;
					}
				}
				while (!queue.isEmpty()) {
					int prev = queue.peek();
					queue.poll();
					if (queue.isEmpty() || prev != queue.peek()) {
						board[R][index--] = prev;
					}
					if (!queue.isEmpty() && prev == queue.peek()) {
						prev *= 2;
						queue.poll();
						board[R][index--] = prev;
					}
				}
			}
			return;
		}
	}

	private static int getMax() {
		int maxValue = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				maxValue = Math.max(maxValue, board[i][j]);
		return maxValue; 
	}
}
