package Samsung_A_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution {
	static int N;
	static int[][] map;
	static int[][] clients = new int[5][2]; // 고객번호 1 ~ num번
	static int[][] monsters = new int[5][2]; // 몬스터번호 1 ~ num번
	static int[] plan = new int[5];
	static boolean[] isVisited = new boolean[5];
	static int num;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 00. 입력한다.
			N = Integer.parseInt(br.readLine());
			map = new int[N+1][N+1];
			num = 0;
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 1; j <= N; j++) {
					int a = Integer.parseInt(st.nextToken());
					if(a < 0) {
						num++;
						clients[Math.abs(a)][0] = i;
						clients[Math.abs(a)][1] = j;
					}else if(a > 0) {
						monsters[a][0] = i;
						monsters[a][1] = j;
					}
				}
			}
			Arrays.fill(isVisited, false);
			Arrays.fill(plan, 0);
			// 01. 찾아보자.
			result.append("#").append(tc).append(" ").append(findRoute(1)).append("\n");
		}
		System.out.println(result.toString());
	}
	
	private static int findRoute(int cnt) {
		if(cnt > num) {
			for(int i = 1; i <= num; i++) {
				System.out.print(plan[i]);
			}System.out.println();
			int res = calTime();
			System.out.println(res);
			return res;
		};
		int result = Integer.MAX_VALUE;
		for(int i = 1; i <= num; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				plan[cnt] = i; 
				result = Math.min(result, findRoute(cnt+1));
				isVisited[i] = false;
			}
		}
		return result;
	}
	
	private static int calTime() {
		int hr = 1;
		int hc = 1;
		int sum = 0;
		for(int i = 1; i <= num; i++) {
			int idx = plan[i];
			int mr = monsters[idx][0];
			int mc = monsters[idx][1];
			int cr = clients[idx][0];
			int cc = clients[idx][1];
			sum += Math.abs(mr-hr) + Math.abs(mc-hc) + Math.abs(mr - cr) + Math.abs(mc - cc);
			hr = cr;
			hc = cc;
		}
		return sum;
	}
			
}
