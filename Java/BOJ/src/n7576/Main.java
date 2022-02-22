package n7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
	int r;
	int c;
	public Tomato(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
public class Main {
	static boolean[][] storage;
	// 상, 우, 하, 좌
	static int[] rowDir = { -1, 0, 1, 0 };
	static int[] colDir = { 0, 1, 0, -1 };
	static Queue<Tomato> queue = new LinkedList<Tomato>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		storage = new boolean[N][M];

		// 00. 0인 입력만 탐색 대상이므로, boolean으로 입력 받는다.
		int kidTmt = 0;
		int in;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				in = Integer.parseInt(st.nextToken());
				// 01. 입력 시 덜 익은 토마토의 개수를 세어준다.
				if (in == 0) {
					storage[i][j] = true;
					kidTmt++;
				}
				if (in == 1) {
					queue.offer(new Tomato(i, j));
				}
			}
		}
		int day = -1;
		int len;
		int nr, nc;
		// 02. BFS로 탐색한다.
		while (!queue.isEmpty()) {
			len = queue.size();
			for (int i = 0; i < len; i++) {
				// 02-01. 큐에 있는 모든 저장고에 대하여, 
				Tomato tomato = queue.poll();
				
				// 02-02. 다음 방문할 저장고 위치를 탐색해,
				for (int dir = 0; dir < 4; dir++) {
					nr = tomato.r + rowDir[dir];
					nc = tomato.c + colDir[dir];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || !storage[nr][nc]) {
						continue;
					}
					// 02-03. 방문 표시를 하고 큐에 추가한다.
					storage[nr][nc] = false;
					queue.offer(new Tomato(nr, nc));
					kidTmt--;
				}
			}
			day++;
		}
		// 03. 덜 익은 토마토가 아직 있다면, -1을 출력한다.
		System.out.println(kidTmt > 0 ? -1 : day);
	}
}
