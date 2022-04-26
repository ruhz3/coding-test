package n4385_별자리_만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Star {
	int num;
	double x, y;
	
	public Star(int num, double x, double y) {
		super();
		this.num = num;
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> {
	int start;
	int end;
	double weight;

	public Edge(int start, int end, double weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		return weight < o.weight ? -1 : 1;
	}
}

public class Main {
	static List<Edge> list = new ArrayList<>();
	static Star[] stars;
	static int[] parents;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		stars = new Star[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(i, x, y);
		}
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		// 01. list에 간선 정보를 저장하고, 비용 기준 정렬한다. 
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				Star a = stars[i];
				Star b = stars[j];
				double dist = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
				list.add(new Edge(a.num, b.num, dist));
			}
		}
		Collections.sort(list);

		// 02. union-find, Kruskal을 사용한다.
		double answer = 0;
		for (Edge edge : list) {
			if (find(edge.start) != find(edge.end)) {
				answer += edge.weight;
				union(edge.start, edge.end);
			}
		}
		System.out.println(answer);
	}

	/* union find*/
	public static int find(int x) {
		if (x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x != y) parents[y] = x;
	}
}