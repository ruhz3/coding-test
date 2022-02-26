package n2563_색종이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] paper = new int[100][100];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			for(int r = row; r < row + 10; r++) {
				for(int c = col; c < col + 10; c++) {
					if(paper[r][c] == 0) {
						paper[r][c] = 1;
						sum++;
					}
				}
			}
		}
		System.out.println(sum);
	}
}
