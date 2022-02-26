package n2116_주사위_쌓기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// ABCDEF -> 012345
	static int[] map = {5, 3, 4, 1, 2, 0};
	static int[][] dice;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 00. 첫번째 주사위의 밑면을 정한다.
		int maxSum = 0;
		for(int i = 0; i < 6; i++) {
			int bottomIdx = i;
			int topIdx = map[i];
			
			// 01. 현재 밑면 기준 측면 최고값을 구한다.
			int max = 0;
			for(int j = 0; j < 6; j++) {
				if(j != bottomIdx && j != topIdx)
					max = Math.max(max, dice[0][i]);
			}
			
			// 02. 현재 밑면을 기준으로 탑을 쌓은 결과를 반환한다.
			maxSum = Math.max(maxSum, max + maxDiceSum(1, dice[0][topIdx]));
		}
		System.out.println(maxSum);
		
	}
	public static int maxDiceSum(int count, int bottom) {
		if(count == N)
			return 0;
		
		// 00. 이전 주사위의 윗면과 일치하는 면을 밑면으로 설정한다.
		int bottomIdx = 0;
		for(int i = 0; i < 6; i++) {
			if(dice[count][i] == bottom) {
				bottomIdx = i;
				break;
			}
		}
		// 01. 밑면의 맞은편 면을 찾는다.(다음 주사위의 밑면이 된다)
		int topIdx = map[bottomIdx];
		
		// 02. 윗면, 밑면을 제외하고 측면 최고 값을 구한다.
		int max = 0;
		for(int i = 0; i < 6; i++) {
			if(i != topIdx && i != bottomIdx)
				max = Math.max(max, dice[count][i]);
		}
		// 03. 위에 쌓을 주사위들 옆면 최댓값의 합과 자신을 더해 반환한다.
		return max + maxDiceSum(count+1, dice[count][topIdx]);
	}
}
