package n1753_최단경로;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
	int id;
	int cost;
	public Edge(int id, int cost) {
		this.id = id;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {    
    static int V,E,K;
    static int u,v,w;
    static int [] dist;
    static ArrayList<Edge>[] adjList;
    static PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>(); 
        
    public static void main(String[] args) throws Exception  {
    	StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 00. 입력한다.
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        dist = new int[V+1];
        for (int i=1; i<=V; i++) {
        	dist[i] = Integer.MAX_VALUE;
        }
        adjList = new ArrayList[V+1];
        for (int i=1; i<=V; i++) {
        	adjList[i] = new ArrayList<Edge>();
        }
        for (int i=1; i<=E; i++) {
        	st = new StringTokenizer(br.readLine());
        	u = Integer.parseInt(st.nextToken());
        	v = Integer.parseInt(st.nextToken());
        	w = Integer.parseInt(st.nextToken());
        	adjList[u].add(new Edge(v,w));
        }
        
        // 01. K에서 출발해 우선순위 큐 이용, 다익스트라 순회한다.
        dist[K] = 0;
        pQueue.add(new Edge(K,0));
        
        while(!pQueue.isEmpty()) {
        	Edge now = pQueue.poll();
        	if (now.cost > dist[now.id]) continue;
        	
        	int len = adjList[now.id].size();
        	for (int i = 0; i<len; i++) {
        		Edge next = (Edge) adjList[now.id].get(i);
        		if (dist[next.id] > now.cost + next.cost ) {
        			dist[next.id] = now.cost + next.cost;
        			pQueue.add(new Edge(next.id, dist[next.id]));
        		}
        	}
        }
        // 02. 출력한다.
        for (int i=1; i<=V; i++) {
        	if (dist[i] == Integer.MAX_VALUE) result.append("INF\n");
        	else result.append(dist[i]+"\n");
        }
        System.out.println(result.toString());
    }    
}