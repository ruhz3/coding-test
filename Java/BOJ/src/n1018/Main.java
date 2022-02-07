package n1018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		char[][] board = new char[50][50];
		char[][] mask = {
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'}
		};
		
		// 00. 입력을 받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i][j] = input.charAt(j);
			}
		}
		
		// 01. 정해놓은 체스판과 비교해 몇개를 바꿔야 하는지 최솟값과 최댓값을 센다.
		int minCount = 64;
		int maxCount = 0;
		for(int i = 0; i <= N-8; i++) {
			for(int j = 0; j <= M-8; j++) {
				//  01-1. (i, j)는 검사하려는 구역의 좌상단 원소이다.
				int count = 0;
				for(int r = 0; r < 8; r++) {
					for(int c = 0; c < 8; c++) {
						// 01-2. 기준이 된 체스판과 색상이 다르다면 count를 센다. 
						if(board[i+r][j+c] != mask[r][c]) { 
							count++;
						}
					}
				}
				// 01-3. 최댓값을 세는 이유는, 체스판 색상이 완전히 역전된 경우가 하나 더 있기 때문이다. 
				if(count < minCount) minCount = count;
				if(count > maxCount) maxCount = count;
			}
		}
		// 02. 두 체스판 중 더 큰 값을 출력한다.
		System.out.println(minCount < 64-maxCount ? minCount : 64-maxCount);
	}
}
