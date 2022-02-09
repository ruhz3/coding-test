package n3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Snake {
	int[][] map;
	int N;
	
	// 상(0), 우(1), 하(2), 좌(3)
	int[] rowDir = {-1, 0, 1, 0};
	int[] colDir = {0, 1, 0, -1};
	int dir = 1;
	
	int headRow = 0;
	int headCol = 0;
	int tailRow = 0;
	int tailCol = 0;

	public Snake(int[][] map) {
		this.map = map;
		N = map.length;
	}
	
	public boolean move() {
		// 00. 꼬리가 뒤따라올 수 있도록 map에 나아갈 방향을 표시해준다.
		map[headRow][headCol] = dir;
		
		// 01. 머리를 옮긴다
		headRow += rowDir[dir];
		headCol += colDir[dir];
		// 01-1. (탈락)맵에 머리를 박은 경우 
		if(headRow < 0 || headRow >= N || headCol < 0 || headCol >= N ) {
			return false;
		}
		// 01-2. (탈락)몸에 머리를 박은 경우
		if((map[headRow][headCol] == 0) || (map[headRow][headCol] == 1) || (map[headRow][headCol] == 2) || (map[headRow][headCol] == 3)){
			return false;
		}
		// 02. 꼬리를 옮기고 머리가 표시했던 방향을 지운다.(사과 판정)
		if(map[headRow][headCol] != 7) {
			int tailDir = map[tailRow][tailCol];
			map[tailRow][tailCol] = -1;
			tailRow += rowDir[tailDir];
			tailCol += colDir[tailDir];
		}
		return true;
	}
	
	public void changeDir(char key) {
		if(key == 'L') {
			dir -= 1;
			dir = dir < 0 ? 3 : dir;
		} else {
			dir += 1;
			dir = dir > 3 ? 0 : dir;
		}
	}
	
	public void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == headRow && j == headCol) {
					System.out.printf("%3c", '*');
				} else {
					System.out.printf("%3d", map[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println("head :" + headRow + ",  " + headCol);
		System.out.println("tail :" + tailRow + ",  " + tailCol);
		System.out.println("dir :" + dir);
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		// 00. map은 -1로 초기화한다.
		/*
		 * -1 : 빈 공간
		 * 0  : 뱀(상)
		 * 1  : 뱀(우)
		 * 2  : 뱀(하)
		 * 3  : 뱀(좌)
		 * 7  : 사과
		 */
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(map[i], -1);
		}
		
		// 01. 문제의 좌표 입력은 0 시작이 아니라 1 시작이므로, 1을 뺴준다.
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 7;
		}
		
		// 02. 시간대별 행동을 미리 저장해둔다.
		int L = Integer.parseInt(br.readLine());
		char[] timeTable = new char[10001];
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			timeTable[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
		}
		
		// 03. Snake를 생성하고 map과 연결해준다.
		Snake snake = new Snake(map);
		
		// 04. 게임을 시작한다.
		int time = 1;
		boolean isAlive = true;
		while(true) {
			// 04-1. 움직인다.
			isAlive = snake.move();
			
			// 04-2. 움직인 결과 죽었다면 탈출한다.
			if(!isAlive) {
				break;
			}
			// 04-3. 해당 초가 끝난 다음,예약된 행동이 있다면 방향울 전환한다.
			if(timeTable[time] != 0) {
				snake.changeDir(timeTable[time]);
			}
			time++;
		}
		System.out.println(time);
	}
}
