package n15591_MooTube_Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int idx;
	int edge;
	public Pair(int idx, int edge) {
		this.idx = idx;
		this.edge = edge;
	}
}
public class Main {
	static Queue<Integer> queue;
	static ArrayList<Pair>[] map;
	static boolean[] isVisited;
	static int N, Q;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		queue = new LinkedList<Integer>();
		map = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			map[i] = new ArrayList<Pair>(); 
		}
		isVisited = new boolean[N+1];
		
		// 01. 인접리스트를 생성한다.
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[a].add(new Pair(b, weight));
			map[b].add(new Pair(a, weight));
		}
		
		// 02. BFS 탐색한다.
		StringBuilder result = new StringBuilder();
		for(int i = 1; i <= Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			Arrays.fill(isVisited, false);
			result.append(find(k, v)).append("\n");
		}
		System.out.println(result.toString());
	}
	
	private static int find(int k, int v) {
		// BFS 탐색(항상 큐에 추가하기 전에 방문체크!)
		int count = 0;
		isVisited[v] = true;
		queue.add(v);
		while(!queue.isEmpty()) {
			int n = queue.poll();
			ArrayList<Pair> list = map[n];
			for(Pair p : list) {
				if(isVisited[p.idx] || p.edge < k) continue;
				isVisited[p.idx] = true;
				queue.add(p.idx);
				count++;
			}
		}
		return count;
	}
}
