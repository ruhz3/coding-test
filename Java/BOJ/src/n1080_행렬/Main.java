package n1080_행렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] A, B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new char[N][];
		B = new char[N][];
		for(int i = 0; i < N; i++) A[i] = br.readLine().toCharArray();
		for(int i = 0; i < N; i++) B[i] = br.readLine().toCharArray();
		
		// 00. 필요한 수를 구한다.
		int count = 0;
		for(int i = 0; i < N - 2; i++) {
			for(int j = 0; j < M - 2; j++) {
				if(A[i][j] != B[i][j]) {
					reverse(i, j);
					count++;
				}
			}
		}
		// 01. 결과를 검산한다.
		boolean ok = true;
	OUTER : for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(A[i][j] != B[i][j]) {
					ok = false;
					break OUTER;
				}
			}
		}
		if(ok) System.out.println(count);
		else System.out.println(-1);
	}
	 
	private static void reverse(int r, int c) {
		for(int i = r; i < r + 3; i++) {
			for(int j = c; j < c + 3; j++) {
				A[i][j] = (A[i][j] == '0') ? '1' : '0';
			}
		}
	}
}
