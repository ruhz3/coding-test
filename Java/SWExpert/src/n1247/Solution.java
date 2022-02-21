package n1247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] office = new int[2];
	static int[] home = new int[2];
	static int[][] customers = new int[10][2];
	static int[] plan = new int[10];
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 00. 입력 받는다.
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			office[0] = Integer.parseInt(st.nextToken());
			office[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			for(int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			// 01. 순회 계획을 초기화 한다.
			for(int i = 0; i < N; i++) {
				plan[i] = i;
			}
			
			// 02. 모든 순회 계획을 검토하여 가장 최소값을 찾는다.
			int minSum = Integer.MAX_VALUE;
			do {
				minSum = Math.min(minSum, getRouteTotalDistance());
			} while(nextPermutation());
			result.append("#").append(tc).append(" ").append(minSum).append("\n");
		}
		
		// 03. 결과를 출력한다.
		System.out.println(result.toString());
	}
	
	private static int getRouteTotalDistance() {
		int x = office[0];
		int y = office[1];
		int sum = 0;
		for(int i = 0; i < N; i++) {
			int idx = plan[i];
			sum += getDistance(x, y, customers[idx][0], customers[idx][1]);
			x = customers[idx][0];
			y = customers[idx][1];
		}
		return sum + getDistance(x, y, home[0], home[1]);
	}
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}
	
	/* next permutation */
	private static boolean nextPermutation() {
		// 00. 뒤에서 시작했을 때 오름차순이 끝나는 값을 찾아준다.
		int i = N-1;
		while(i > 0 && plan[i - 1] >= plan[i]) --i;
		if(i == 0) return false;
		
		// 01. 위에서 찾은 값 바로 앞 수와 바꿀 수를 결정한다.
		int j = N-1;
		while(plan[i - 1] >= plan[j]) --j;
		
		// 02. 바꿔준다.
		int tmp = plan[i - 1];
		plan[i - 1] = plan[j];
		plan[j] = tmp;
		
		// 03. 뒷 부분을 오름차순으로 정렬해준다.
		int k = N-1;
		while(i < k) {
			tmp = plan[i];
			plan[i++] = plan[k];
			plan[k--] = tmp;
		}
		return true;
	}
}
