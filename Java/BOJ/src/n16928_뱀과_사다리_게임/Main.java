package n16928_뱀과_사다리_게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<Integer> queue = new LinkedList<>();
	static boolean[] isVisited = new boolean[101];
	static int[] board = new int[101];
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N+M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			board[from] = to;
		}
		// 01. BFS 탐색한다.
		int count = 0;
		queue.offer(1);
	OUTER : while(true) {
			for(int i = 0, len = queue.size(); i < len; i++) {
				int pos = queue.poll();
				for(int dice = 1; dice <= 6; dice++) {
					int nextPos = pos + dice;
					if(nextPos >= 100) break OUTER;
					if(board[nextPos] != 0) nextPos = board[nextPos];
					if(!isVisited[nextPos]) {
						queue.offer(nextPos);
						isVisited[nextPos] = true;
					}
				}
			}
			count++;
		}
		// 02. 주사위 던진 횟수를 출력한다.
		System.out.println(count + 1);		
	}
}
