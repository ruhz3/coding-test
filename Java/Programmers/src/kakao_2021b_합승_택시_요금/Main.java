package kakao_2021b_합승_택시_요금;

import java.util.Arrays;

public class Main {
	static int[] ns = { 6, 7, 6 };
	static int[] ss = { 4, 3, 4 };
	static int[] as = { 6, 4, 5 };
	static int[] bs = { 2, 1, 6 };
	static int[][][] faress = {
			{{ 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 }, { 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 }},
			{{ 5, 7, 9 }, { 4, 6, 4 }, { 3, 6, 1 }, { 3, 2, 3 }, { 2, 1, 6 }},
			{{ 2, 6, 6 }, { 6, 3, 7 }, { 4, 6, 7 }, { 6, 5, 11 }, { 2, 5, 12 }, { 5, 3, 20 }, { 2, 4, 8 }, { 4, 3, 9 }}
	};
	public static void main(String[] args) {
		for (int i = 0; i < ns.length; i++) {
			int result = new Solution().solution(ns[i], ss[i], as[i], bs[i], faress[i]);
			System.out.print(result + " ");
		}
	}
}
class Solution {
    static final int INF = 987654321;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        int dist[][] = new int[n+1][n+1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(dist[i], INF);
        }
        for(int i = 0; i < fares.length; i++){
            for(int j = 0; j < fares[i].length; j++){
                int c = fares[i][0];
                int d = fares[i][1];
                int f = fares[i][2];
                dist[c][d] = f;
                dist[d][c] = f;
            }
        }
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(i == j) dist[i][j] = 0;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        for(int i = 1; i <= n; i++){
            if(dist[s][i] != INF && dist[s][a] != INF && dist[s][b] != INF){
                answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
            }
        }
        return answer;
    }
}