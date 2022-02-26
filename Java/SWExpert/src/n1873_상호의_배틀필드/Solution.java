package n1873_상호의_배틀필드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1873_상호의_배틀필드
 * 메모리 :	22,004 kb
 * 실행시간 :	121 ms
 */

class Tank {
	final int[] rowDir = { -1, 1, 0, 0 };
	final int[] colDir = { 0, 0, -1, 1 };
	char[] tankChar = { '^', 'v', '<', '>' };
	char[][] map;
	int H, W;
	int tankRow;
	int tankCol;
	int tankDir;

	public Tank(char[][] map, int tankRow, int tankCol, int tankDir) {
		this.map = map;
		H = map.length;
		W = map[0].length;
		this.tankRow = tankRow;
		this.tankCol = tankCol;
		this.tankDir = tankDir;
	}
	
	/* 입력 키에 따라 행동을 결정하는 함수*/
	public void act(char key) {
		switch (key) {
		// 방향 키
		case 'U':
			move(0);
			break;
		case 'D':
			move(1);
			break;
		case 'L':
			move(2);
			break;
		case 'R':
			move(3);
			break;
		// 발사 키
		case 'S':
			shoot(tankRow, tankCol);
			break;
		}
	}
	/* 탱크를 움직이는 함수*/
	public void move(int dir) {
		// 00. 일단 방향은 돌아 준다.
		tankDir = dir;
		map[tankRow][tankCol] = tankChar[dir];

		// 01. 움직이는 건 그 다음!
		int nr = tankRow + rowDir[tankDir];
		int nc = tankCol + colDir[tankDir];

		if (nr < 0 || nr >= H || nc < 0 || nc >= W)
			return;
		if (map[nr][nc] == '.') {
			map[tankRow][tankCol] = '.';
			map[nr][nc] = tankChar[dir];
			tankRow = nr;
			tankCol = nc;
		}
	}
	/* 포탄을 쏘는 함수*/
	public void shoot(int bulletRow, int bulletCol) {
		int nr = bulletRow + rowDir[tankDir];
		int nc = bulletCol += colDir[tankDir];

		// 00. 나가거나 강철 벽을 만나면 아무 일도 일어나지 않는다.
		if (nr < 0 || nr >= H || nc < 0 || nc >= W)
			return;
		if (map[nr][nc] == '#')
			return;
		
		// 01. 벽돌 벽은 부순다.
		if (map[nr][nc] == '*') {
			map[nr][nc] = '.';
			return;
		}
		
		// 02. 아무 일도 없었다면, 해당 방향 다음 블록으로 간다.
		shoot(nr, nc);
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			// 00. 이차원 배열 map을 입력 받고, 탱크를 생성한다.
			char[][] map = new char[H][W];

			int cr = 0;
			int cc = 0;
			int cd = 0;
			for (int i = 0; i < H; i++) {
				String input = br.readLine();
				for (int j = 0; j < W; j++) {
					char key = input.charAt(j);
					map[i][j] = key;
					switch (key) {
					case '^':
						cr = i;
						cc = j;
						cd = 0;
						break;
					case 'v':
						cr = i;
						cc = j;
						cd = 1;
						break;
					case '<':
						cr = i;
						cc = j;
						cd = 2;
						break;
					case '>':
						cr = i;
						cc = j;
						cd = 3;
						break;
					default:
						break;
					}
				}
			}
			Tank tank = new Tank(map, cr, cc, cd);

			// 01. 탱크를 조작할 키를 입력 받는다.
			int N = Integer.parseInt(br.readLine());
			char[] act = new char[N];

			String input = br.readLine();
			for (int i = 0; i < N; i++) {
				act[i] = input.charAt(i);
			}

			// 02. 키를 눌러보자.
			for (int i = 0; i < N; i++) {
				tank.act(act[i]);
			}

			// 03. 맵을 확인해보자.
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					result.append(map[i][j]);
				}
				result.append("\n");
			}
		}
		System.out.println(result.toString());
	}
}
