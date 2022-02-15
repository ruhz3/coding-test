package n16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] level;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()); 
		
		// 00. 숫자를 입력 받는다.
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 01. 참고할 레벨 맵을 생성한다.
		level = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int rowDist = Math.min(j, (M-1)-j);
				int colDist = Math.min(i, (N-1)-i);
				level[i][j] = Math.min(rowDist, colDist);
			}
		}
		
		// 02. 현재 위치에서 회전했을 때의 좌표를 출력한다.
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int lv = level[i][j];
				int nr = i;
				int nc = j;
				for(int k = 0, r = R % (2 * (N-2*lv-1) + 2 * (M-2*lv-1)); k < r; k++) {
					// 윗 좌표를  가져와야하는 구역
					if((lv < nr && nr <= (N-1)-lv) && (nc == lv)) nr--;
					// 왼쪽 좌표를  가져와야하는 구역
					else if((nr == (N-1)-lv) && (lv < nc && nc <= (M-1)-lv)) nc--;
					// 아래쪽 좌표를 가져와하는 구역
					else if((lv <= nr && nr < (N-1)-lv) && (nc == (M-1)-lv)) nr++;
					// 오른쪽으로 좌표를 가져와야 하는 구역
					else if((nr == lv) && (lv <= nc && nc < (M-1)-lv)) nc++;
					
				}
				result.append(map[nr][nc]).append(" ");
			}
			result.append("\n");
		}
		System.out.println(result.toString());
	}
}
