package weekly_전력망을_둘로_나누기;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	Queue<Integer> queue = new LinkedList<>();
	boolean[] isVisited;
	boolean[][] adj;
	int N;

	public int solution(int n, int[][] wires) {
		N = n;
		isVisited = new boolean[N + 1];
		adj = new boolean[N + 1][N + 1];
		// 00. 인접 리스트를 초기화한다.
		for (int i = 0, len = wires.length; i < len; i++) {
			int a = wires[i][0];
			int b = wires[i][1];
			adj[a][b] = adj[b][a] = true;
		}
		// 01. 하나를 끊어보고 그에 따른 최소 차이를 구한다.
		int minGap = Integer.MAX_VALUE;
		for (int i = 0, len = wires.length; i < len; i++) {
			int a = wires[i][0];
			int b = wires[i][1];
			adj[a][b] = adj[b][a] = false;
			int gap = getMinGap();
			adj[a][b] = adj[b][a] = true;
			if (gap == -1) continue;
			minGap = Math.min(gap, minGap);
		}
		return minGap;
	}
	
	/* 둘로 나눴을 때 차이를 반환하며, 둘로 나눠지지 않는다면 -1을 반환한다.*/
	private int getMinGap() {
		// 00. 방문 배열을 초기화한다.
		Arrays.fill(isVisited, false);
		int groupCount = 0;
		int[] values = { 0, 0 };
		
		for (int i = 1; i <= N; i++) {
		// 01. 방문하지 않은 노드가 있다면, 해당 노드 기준으로 bfs 순회한다.
			if (!isVisited[i]) values[groupCount++] = bfs(i);
		// 02. 2개 그룹까지 순회했다면 반환할 준비를 한다.
			if (groupCount >= 2) break;
		}
		
		// 03. 2개 그룹까지 만들었는데 아직 방문하지 못한 노드가 있다면, 이미 그룹이 3개 이상이다.
		for (int i = 1; i <= N; i++)
			if (!isVisited[i]) return -1;
		// 04. 차이를 반환한다.
		return Math.abs(values[0] - values[1]);
	}
	
	/* 해당 노드에서 연결된 노드를 모두 BFS 탐색하고, 그룹의 크기를 반환한다.*/
	private int bfs(int node) {
		// 00. 큐를 초기화한다.
		int count = 1;
		queue.clear();
		isVisited[node] = true;
		queue.offer(node);
		
		// 01. 해당 노드 기준으로 방문할 수 있는 모든 노드를 방문한다.
		while (!queue.isEmpty()) {
			int n = queue.poll();
			for (int i = 1; i <= N; i++)
				if (adj[n][i] && !isVisited[i]) {
					isVisited[i] = true;
					queue.offer(i);
					count++;
				}
		}
		// 02. 방문이 끝나면 그룹의 크기를 반환한다.
		return count;
	}
}