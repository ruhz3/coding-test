package SKT_02_03;

public class SKT_02_03 {
	static int[][] a1 = {{1, 2}, {2, 3}};
	static int[][] b1 = {{1, 3}, {3, 2}};
	static int m1 = 1;
	
	public static void main(String[] args) {
		int res = new Solution().solution(a1, b1, m1);
		System.out.println(res);
	}
}

class Solution {
	boolean[][] adj;
    public int solution(int[][] a, int[][] b, int m) {
        int answer = -1;
        adj = new boolean[12][12];
        for(int i = 0; i < a.length; i++) {
        	adj[a[i][0]][a[i][1]] = true;
        	adj[a[i][1]][a[i][0]] = true;
        }
        return answer;
    }
    
    private void action1(int node, int oldEdge, int newEdge) {
    	adj[node][oldEdge] = false;
    	adj[oldEdge][node] = false;
    	
    	adj[node][newEdge] = true;
    	adj[newEdge][node] = true;
    }
    
    private void action2(int a, int b) {
    	boolean[] tmp = adj[a];
    	adj[a] = adj[b];
    	adj[b] = tmp;
    }
}