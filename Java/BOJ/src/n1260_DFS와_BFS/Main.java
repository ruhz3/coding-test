package n1260_DFS와_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder result;
	static boolean[][] nodes;
	static boolean[] isVisited;
	static int N, M, V;
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		nodes = new boolean[N+1][N+1];
		isVisited = new boolean[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodes[r][c] = true;
			nodes[c][r] = true;
		}
		DFS(V);
		Arrays.fill(isVisited, false);
		result.append("\n");
		BFS(V);
		
		System.out.println(result.toString());
	}
	
	private static void DFS(int num) {
		// 00. 방문했다.
		isVisited[num] = true;
		result.append(num).append(" ");
		
		// 01. 연결된 더 깊게 방문할 노드가 있는가?
		for(int i = 1; i <= N; i++) {
			if(nodes[num][i] && !isVisited[i]) {
				DFS(i);
			}
		}
		return;
	}
	
	private static void BFS(int num) {
		if(isVisited[num]) {
			queue.poll();
			return;
		}
		isVisited[num] = true;
		result.append(num).append(" ");
		for(int i = 1; i <= N; i++) {
			if(nodes[num][i] && !isVisited[i]) {
				queue.add(i);
			}
		}
		if(!queue.isEmpty())
			BFS(queue.poll());
		
		return;
	}
}
