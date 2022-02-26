package n17144_미세먼지_안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] room;
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
	static int R, C, T;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 00. 입력한다.
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		for(int i = 0; i < R; i++) { 
			st = new StringTokenizer(br.readLine(), " ");	
			for(int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 01. 공기청정기의 위치를 찾는다.
		int airCleanerRow = 0;
		for(int i = 0; i < R; i++) {
			if(room[i][0] == -1) {
				airCleanerRow = i;
				break;
			}
		}
		// 02. 조건을 실행한다.
		int time = 1;
		while(time <= T) {
			diffuse();
			circulateUp(airCleanerRow);
			circulateDown(airCleanerRow+1);
			time++;
		}
		// 03. 미세먼지 양을 출력한다.
		int sum = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sum += room[i][j];
			}
		}
		System.out.println(sum + 2);
	}
	
	private static void diffuse() {
		
		/* 2차원 배열은 다음과 같이 깊은 복사 해줘야한다.*/
		int[][] newRoom = new int[R][];
		for(int i = 0; i < R; i++) {
			newRoom[i] = room[i].clone();
		}
		
		// 00. 방의 r, c 위치에 대하여,
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				
				// 01. 공기청정기거나 빈 공기가 아니라면 4방으로 확산시켜준다.
				if(room[r][c] == -1 || room[r][c] == 0) continue;
				int sum = 0;
				for(int dir = 0; dir < 4; dir++) {
					int nr = r + rowDir[dir];
					int nc = c + colDir[dir];
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					if(room[nr][nc] == -1) continue;
					newRoom[nr][nc] += (room[r][c] / 5);
					sum += (room[r][c] / 5);
				}
				newRoom[r][c] -= sum;
			}
		}
		// 02. 원래 배열에 얕은 복사한다.
		room = newRoom;
		return;
	}
	
	private static void circulateUp(int coord) {
        for (int i = coord - 1; i > 0; i--) 
        	room[i][0] = room[i-1][0];
        for (int i = 0; i < C - 1; i++) 
        	room[0][i] = room[0][i+1];
        for (int i = 0; i < coord; i++) 
        	room[i][C - 1] = room[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--) 
        	room[coord][i] = room[coord][i-1];
        
        room[coord][1] = 0;
	}
	
	private static void circulateDown(int coord) {
        for (int i = coord + 1; i < R - 1; i++) 
        	room[i][0] = room[i + 1][0];
        for (int i = 0; i < C - 1; i++) 
        	room[R - 1][i] = room[R - 1][i + 1];
        for (int i = R - 1; i > coord; i--) 
        	room[i][C - 1] = room[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--) 
        	room[coord][i] = room[coord][i - 1];
        
        room[coord][1] = 0;
	}
}
