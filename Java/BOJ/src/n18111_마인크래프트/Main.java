package n18111_마인크래프트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int N, M, B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 00. 입력한다.
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int minTime = Integer.MAX_VALUE;
		int minHeight = Integer.MAX_VALUE;
		for(int height = 0; height <= 256; height++) {
			int deleteCount = 0;
			int addCount = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] > height) deleteCount += map[i][j] - height;
					if(map[i][j] < height) addCount += height - map[i][j];
				}
			}
			if(addCount > deleteCount + B) break;
			int time = addCount + deleteCount*2;
			if(time <= minTime) {
				minTime = time;
				minHeight = height;
			}
		}
		System.out.println(minTime + " " + minHeight);
	}
}
