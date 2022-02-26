package n2615_오목;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// * 방향은 순서대로 ↓, →, ↘, ↗
	private static int[] rowDir = {1, 0, 1, -1};
	private static int[] colDir = {0, 1, 1, 1};
	private static int[][] board;
	
	// * 해당 방향으로 몇 개의 같은 돌이 존재하는지 확인하는 함수
	public static int[] countLine(int dir, int r, int c) {
		int prev = -1;
		int[] result = new int[3];
		
		int nr = r;
		int nc = c;
		int count = 0;
		
		while(true) {
			// 01. count가 5일 때, 돌이 바뀌었거나 끝났다면 찾았다! (조건을 분리해 IndexError를 방지)
			if(count == 5 && (nr < 0 || nr >= 19 || nc < 0 || nc >= 19)) {
				return result;
			}
			if(count == 5 && board[nr][nc] != prev) {
				return result;
			}
			
			// 02. 해당 경우가 아닌데 범위 밖으로 나갔다면 해당사항 없다. 
			if(nr < 0 || nr >= 19 || nc < 0 || nc >= 19) {
				result[0] = -1;
				result[1] = -1;
				result[2] = -1;
				return result;
			}
			
			// 03-1. 빈 칸은 그냥 건너뛰고, 다시 초기화 해주자.
			if(board[nr][nc] == 0) {
				prev = board[nr][nc];
				count = 0;
			}else {
				// 03-2. 기존에 보던 돌과 종류가 다르다면 새롭게 count 시작한다. 
				if(prev != board[nr][nc]) {
					result[0] = nr+1;
					result[1] = nc+1;
					result[2] = board[nr][nc];
					prev = board[nr][nc];
					count = 1;
				}
				// 03-3. 기존에 보던 돌과 종류가 같다면 count를 계속한다. 
				else {
					count ++;
				}
			}
			
			// 04. 해당 방향 다음 돌을 바라본다. 
			nr += rowDir[dir];
			nc += colDir[dir];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 00. 바둑판을 입력 받는다.
		board = new int[19][19];
		for(int i = 0; i < 19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 19; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 01. 각 방향을 검사한다. 
		int[] result;
		
		// ↓ 방향 일괄검사
		for(int j = 0; j < 19; j++) {
			result = countLine(0, 0, j);
			if(result[0] == -1) {
				continue;
			} else {
				System.out.println(result[2]);
				System.out.println(result[0] + " " + result[1]);
				return;
			}
		}
		
		// → 방향 검사
		for(int i = 0; i < 19; i++) {
			result = countLine(1, i, 0);
			if(result[0] == -1) {
				continue;
			} else {
				System.out.println(result[2]);
				System.out.println(result[0] + " " + result[1]);
				return;
			}
		}
		
		// ↘ 방향 검사
		for(int j = 0; j < 19; j++) {
			result = countLine(2, 0, j);
			if(result[0] == -1) {
				continue;
			} else {
				System.out.println(result[2]);
				System.out.println(result[0] + " " + result[1]);
				return;
			}
		}
		for(int i = 1; i < 19; i++) {
			result = countLine(2, i, 0);
			if(result[0] == -1) {
				continue;
			} else {
				System.out.println(result[2]);
				System.out.println(result[0] + " " + result[1]);
				return;
			}
		}
		
		// ↗ 방향 검사
		for(int i = 0; i < 19; i++) {
			result = countLine(3, i, 0);
			if(result[0] == -1) {
				continue;
			} else {
				System.out.println(result[2]);
				System.out.println(result[0] + " " + result[1]);
				return;
			}
		}
		for(int j = 1; j < 19; j++) {
			result = countLine(3, 18, j);
			if(result[0] == -1) {
				continue;
			} else {
				System.out.println(result[2]);
				System.out.println(result[0] + " " + result[1]);
				return;
			}
		}
		
		// 해당없다면 0 출력!
		System.out.println("0");
	}
}