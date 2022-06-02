package n15683_감시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* 감시 */
class CCTV {
	int[][] map;
	int N, M;

	int r, c;
	int type;
	boolean[] typeDict;

	public CCTV(int r, int c, int type, int[][] map, int N, int M) {
		this.r = r;
		this.c = c;
		this.type = type;
		this.map = map;
		this.N = N;
		this.M = M;
		
		switch (type) {
		case 1: // 타입1 : 우
			typeDict = new boolean[] { false, true, false, false };
			break;
		case 2: // 타입2 : 우좌
			typeDict = new boolean[] { false, true, false, true };
			break;
		case 3: // 타입3 : 상우
			typeDict = new boolean[] { true, true, false, false };
			break;
		case 4: // 타입4 : 상우좌
			typeDict = new boolean[] { true, true, false, true };
			break;
		case 5: // 타입5 : 상우하좌
			typeDict = new boolean[] { true, true, true, true };
			break;
		}
	}
	
	public void rotate(int cnt, boolean reverse) {
		// CCTV의 방향을 90도 회전시켜주고, 옵션으로 역회전시킬 수 있다.
		if (reverse) {
			for (int t = 0; t < cnt; t++) {
				boolean last = typeDict[3];
				for (int i = 2; i >= 0; i--) {
					typeDict[i+1] = typeDict[i];
				}
				typeDict[0] = last;
			}
		} else {
			for (int t = 0; t < cnt; t++) {
				boolean first = typeDict[0];
				for (int i = 1; i < 4; i++) {
					typeDict[i - 1] = typeDict[i];
				}
				typeDict[3] = first;
			}
		}
	}

	public void on() {
		// 상, 우, 하, 좌
		if(typeDict[0]) {
			for(int i = r-1; i >= 0; i--) {
				if(map[i][c] == 0) map[i][c] = -1;
				else if(map[i][c] == 6) break;
			}
		}
		if(typeDict[1]) {
			for(int j = c+1; j < M; j++) {
				if(map[r][j] == 0) map[r][j] = -1;
				else if(map[r][j] == 6) break;
			}
		}
		if(typeDict[2]) {
			for(int i = r+1; i < N; i++) {
				if(map[i][c] == 0) map[i][c] = -1;
				else if(map[i][c] == 6) break;
			}
		}
		if(typeDict[3]) {
			for(int j = c-1; j >= 0; j--) {
				if(map[r][j] == 0) map[r][j] = -1;
				else if(map[r][j] == 6) break;
			}
		}
	}
	public void off() {
		// 상, 우, 하, 좌
		if(typeDict[0]) {
			for(int i = r-1; i >= 0; i--) {
				if(map[i][c] == -1) map[i][c] = 0;
				else if(map[i][c] == 6) break;
			}
		}
		if(typeDict[1]) {
			for(int j = c+1; j < M; j++) {
				if(map[r][j] == -1) map[r][j] = 0;
				else if(map[r][j] == 6) break;
			}
		}
		if(typeDict[2]) {
			for(int i = r+1; i < N; i++) {
				if(map[i][c] == -1) map[i][c] = 0;
				else if(map[i][c] == 6) break;
			}
		}
		if(typeDict[3]) {
			for(int j = c-1; j >= 0; j--) {
				if(map[r][j] == -1) map[r][j] = 0;
				else if(map[r][j] == 6) break;
			}
		}
	}
}

public class Main {
	static int[][] map;
	static int N, M;
	static ArrayList<CCTV> cctvs = new ArrayList<CCTV>();
	static int len;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 00. 입력을 받는다.
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		// 01. 입력 과정에서 CCTV 배열을 생성한다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6)
					cctvs.add(new CCTV(i, j, map[i][j], map, N, M));
			}
		}
		len = cctvs.size();
		
		// 01. 답을 찾아 결과를 출력한다.
		System.out.println(getMinBlindSpot(0));
	}

	private static int getMinBlindSpot(int index){
		// * 방향을 모두 결정했다면 결과를 계산해본다.
		if(index >= len) {
			return calBlindSpot();
		}
		// 각 CCTV의 방향을 결정해본다.
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 4; i++) {
			cctvs.get(index).rotate(i, false);
			min = Math.min(min, getMinBlindSpot(index+1));
			cctvs.get(index).rotate(i, true);
		} 
		return min;
	}

	private static int calBlindSpot() {
		// 00. CCTV를 켜준다.
		for(int i = 0; i < len; i++) {
			cctvs.get(i).on();
		}
		// 01. 현재 감시 불가능한 영역의 크기를 더한다.
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) sum++;
			}
		}
		// 02. CCTV를 꺼준다.
		for(int i = 0; i < len; i++) {
			cctvs.get(i).off();
		}
		return sum;
	}
}
