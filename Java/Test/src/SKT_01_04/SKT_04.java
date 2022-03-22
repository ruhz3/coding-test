package SKT_01_04;

public class SKT_04 {
	public static void main(String[] args) {
		System.out.println(new Solution().solution(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}}));
	}
}

class Solution {
	int N;
	boolean[][] map;
	boolean[] isVisited;
	
    public long solution(int n, int[][] edges) {
    	N = n;
    	map = new boolean[N][N];
    	isVisited = new boolean[N];
    	for(int[] edge : edges) {
    		map[edge[0]][edge[1]] = true;
    		map[edge[1]][edge[0]] = true;
    	}
    	long sum = 0;
    	for(int i = 0; i < N; i++) {
    		isVisited[i] = true;
    		sum += findRoute(i, 1);
    		isVisited[i] = false;
    	}
        long answer = sum;
        return answer;
    }
    
    private long findRoute(int num, int count) {
    	if(count == 3) return 1;
    	long sum = 0;
    	for(int i = 0; i < N; i++) {
    		if(!isVisited[i] && map[num][i]) {
    			isVisited[i] = true;
    			sum += findRoute(i, count+1);
    			sum += findRoute(i, count);
    			isVisited[i] = false;
    		}
    	}
    	return sum;
    }
}