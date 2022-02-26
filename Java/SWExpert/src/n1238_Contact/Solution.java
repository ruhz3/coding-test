package n1238_Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1238_Contact
 * 메모리 :	19,920 kb
 * 실행시간 :	103 ms
 */

public class Solution {
	static Queue<Integer> queue = new LinkedList<>();
	static int[][] graph = new int[100][100];
	static int[] check = new int[100];
	static int N;
	static int S;

	public static void main(String[] args) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			// 00. 초기화한다.
			queue.clear();
			for(int i = 0; i < 100; i++)
				Arrays.fill(graph[i], 0);
			Arrays.fill(check, 0);
			
			// 01. 입력한다.
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken()) - 1;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				graph[from][to] = 1;
			}
			
			// 02. BFS 탐색한다.
			queue.offer(S);
			check[S] = 1;
			int maxCnt = 0;
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				for (int i = 0; i < 100; i++) {
					if (graph[cur][i] != 1 || check[i] != 0)
						continue;
					check[i] = check[cur] + 1;
					queue.offer(i);
				}
				maxCnt = check[cur];
			}
			
			// 03. 문제 조건에 맞게 값을 구해 출력한다.
			int ret = 0;
			for (int i = 0; i < 100; i++) {
				if (maxCnt != check[i])
					continue;
				ret = ret > i ? ret : i;
			}
			result.append("#").append(tc).append(" ").append(ret + 1).append("\n");
		}
		System.out.println(result.toString());
	}

}
