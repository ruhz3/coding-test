import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map = new int[50][50];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		long before = System.currentTimeMillis();
		
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 00. 입력한다.
			int N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 01. 전체 원소에 대하여,
			int maxValue = 0;
			int maxCount = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					
					// 02. 최대 부분배열을 계산한다. 
					for(int r = i; r < N; r++) {
						for(int c = j; c < N; c++) {
							if(map[i][j] == map[r][c]) {
								int area = calArea(i, j, r, c);
								if(area > maxValue) {
									maxValue = area;
									maxCount = 1;
								}
								else if (area == maxValue) {
									maxCount++;
								}
							}
						}
					}
					
				}
			}
			result.append("#").append(tc).append(" ").append(maxCount).append("\n");
		}
		System.out.println(result.toString());
		long after = System.currentTimeMillis();
		System.out.println("실행시간 : " + (after - before) + "ms");
	}
	
	private static int calArea(int sr, int sc, int er, int ec) {
		return (sr - er + 1) * (sc - ec + 1);
	}
}
