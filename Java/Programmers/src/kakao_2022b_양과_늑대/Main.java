package kakao_2022b_양과_늑대;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int[] info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
	static int[][] edges = {
			{ 0, 1 }, { 1, 2 }, { 1, 4 },
			{ 0, 8 }, { 8, 7 }, { 9, 10 },
			{ 9, 11 }, { 4, 3 }, { 6, 5 },
			{ 4, 6 }, { 8, 9 } };
	public static void main(String[] args) {
		int res = new Solution().solution(info, edges);
		System.out.println(res);
	}
}

class Solution {
	List<Integer> borders = new LinkedList<>();
	Queue<Integer> queue = new LinkedList<>();
	int[][] tree;
	int[] type;
	boolean[] isVisited;
	int N;
	
    public int solution(int[] info, int[][] edges) {
        // 00. 값들을 초기화한다.
    	N = info.length;
        type = info;
        tree = new int[N][2];
        isVisited = new boolean[N];
        for(int[] edge : edges) {
        	int[] node = tree[edge[0]];
        	if(node[0] == 0) node[0] = edge[1];
        	else node[1] = edge[1];
        }
        // 01. 가장자리에 있는 노드들 중 가장 가까운 것을 Greedy로 구한다.
        int lambs = 1;
        int score = 1;
        borders.add(0);
        while(true) {
        	for(int i = 0; i < N; i++) {
        		System.out.print(isVisited[i] + " ");
        	} System.out.println();
        // 02. 현재 Border들 중에 가장 양까지의 거리가 짧은 친구를 찾는다.
        	int lowCost = Integer.MAX_VALUE;
        	int lowIndex = 0;
        	for(int border : borders) {
        		int cost = getLowestCost(border);
    			if(cost < lowCost) {
    				lowCost = cost;
    				lowIndex = border;
    			}
        	}
        // 03. 더이상 양이 없다면, 종료한다.
        	if(lowCost == Integer.MAX_VALUE) break;
        	
        // 04. 늑대가 더 많아졌다면, 종료한다.
        	score += 1 - lowCost;
        	if(score <= 0) break;
        	lambs++;
        	expandGroup(lowIndex);
        	
        // 05. 테두리에 있는 친구들을 가져온다.
        	updateBorders();
        }
        
        return lambs;
    }
    /* 해당 노드에서 양에 도달하는 최소 비용을 반환*/
    private int getLowestCost(int node) {
    	queue.clear();
    	queue.offer(node);
    	int cost = 0;
    	while(!queue.isEmpty()) {
    		for(int i = 0, len = queue.size(); i < len; i++) {
    			int n = queue.poll();
    			if(type[n] == 0) return cost;
    			for(int child : tree[n]) {
    				if(child == 0 || isVisited[child]) continue;
    				queue.offer(child);
    			}
    		}
    		cost++;
    	}
    	return Integer.MAX_VALUE;
    }
    /* 그룹을 확장*/
    private void expandGroup(int node) {
    	queue.clear();
    	queue.offer(node);
    	while(!queue.isEmpty()) {
    		int n = queue.poll();
    		for(int child : tree[n]) {
				if(child == 0 || isVisited[child]) continue;
				queue.offer(child);
				isVisited[child] = true;
			}
    	}
    }
    /* 테두리 노드들을 업데이트*/
    private void updateBorders() {
    	borders.clear();
    	queue.clear();
    	queue.offer(0);
    	while(!queue.isEmpty()) {
    		int n = queue.poll();
    		boolean isBorder = false;
    		for(int child : tree[n]) {
    			if(child == 0) continue;
				if(!isVisited[child]) {
					isBorder = true;
					continue;
				}
				queue.offer(child);
			}
    		if(isBorder) borders.add(n);
    	}
    }
}