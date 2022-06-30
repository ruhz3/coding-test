package n11403_경로_찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] matrix;
	static int N;
	
	public static void main(String[] args) throws IOException{
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++)
				matrix[i][j] = st.nextToken().equals("1");
		}
		// 01. 플로이드 워셜로 연결 여부를 조사한다.
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					matrix[i][j] = matrix[i][j] || (matrix[i][k] && matrix[k][j]);
		}
		// 02. 결과를 출력한다.
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				sb.append(matrix[i][j] ? 1 : 0).append(" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
