package n2527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] square1 = new int[2][2];
		int[][] square2 = new int[2][2];
		final int small = 0;
		final int big = 1;
		final int x = 0;
		final int y = 1;
		
		for(int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// 00. 기준이 될 직사각형
			for(int i = 0; i < 2; i++) {
				square1[i][0] = Integer.parseInt(st.nextToken());
				square1[i][1] = Integer.parseInt(st.nextToken());
			}
			for(int i = 0; i < 2; i++) {
				square2[i][0] = Integer.parseInt(st.nextToken());
				square2[i][1] = Integer.parseInt(st.nextToken());
			}	
			
			// 01. x, y좌표를 나눠 비교해보자. (범위로 겹친다 -1, 점으로 겹친다 0, 안 겹친다 1)
			int xCompare = -1;
			if(square2[big][x] == square1[small][x] || square2[small][x] == square1[big][x]) xCompare = 0;
			if(square2[big][x] < square1[small][x] || square2[small][x] > square1[big][x]) xCompare = 1;
			int yCompare = -1;
			if(square2[big][y] == square1[small][y] || square2[small][y] == square1[big][y]) yCompare = 0;
			if(square2[big][y] < square1[small][y] || square2[small][y] > square1[big][y]) yCompare = 1;
			
			// 02. 비교결과 판정하자.
			if(xCompare == 0 && yCompare == 0) result.append("c");
			else if(xCompare == 1 || yCompare == 1) result.append("d");
			else if(xCompare + yCompare == -1) result.append("b");
			else result.append("a");
			result.append("\n");
		}
		System.out.println(result.toString());
	}
}
