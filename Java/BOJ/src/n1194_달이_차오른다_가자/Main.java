package n1194_달이_차오른다_가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Coord {
	int r, c;
	int key;
	public Coord(int r, int c, int key) {
		super();
		this.r = r;
		this.c = c;
		this.key = key;
	}
}

public class Main {
	static char[][] map;
	static int N, M;

	static Queue<Coord> queue = new LinkedList<Coord>();
	static boolean[][][] isVisited;
	static int[] rowDir = {-1, 0, 1, 0};  // 상, 우, 하, 좌
	static int[] colDir = {0, 1, 0, -1};  // 상, 우, 하, 좌
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N][M][1<<6];
		map = new char[N][];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 01. 시작점 좌표를 찾는다.
		Coord start = null;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == '0') {
					start = new Coord(i, j, 0);
					break;
				}
			}
		}
		// 02. 얼마나 걸렸는지, 탈출에 성공했는지를 판별한다.
		int time = 0;
		boolean isEscaped = false;
		
		// 03. BFS 탐색한다.
		queue.add(start);
	OUTER : while(!queue.isEmpty()) {
			// 03-00. 시간을 판별하기 위해, 현 시점 개수만큼 큐에서 꺼내고 while문을 한바퀴 돈다.
			int len = queue.size();
			for(int i = 0; i < len; i++) {
				Coord c = queue.poll();
				if(map[c.r][c.c] == '1') {
					isEscaped = true; 
					break OUTER;
				}
				// 03-01. 4방 탐색하며 경로를 찾는다.
				for(int dir = 0; dir < 4; dir++) {
					int nr = c.r + rowDir[dir];
					int nc = c.c + colDir[dir];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if(map[nr][nc] == '#' || isVisited[nr][nc][c.key]) continue;
					// * 열쇠를 만난 경우 : Coord의 key에 획득한 열쇠를 기록하고 방문한다. 
					if(map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
						int bitKey = (1 << map[nr][nc] - 'a') | c.key;
						if(!isVisited[nr][nc][bitKey]) {
							isVisited[nr][nc][bitKey] = true;
							queue.offer(new Coord(nr, nc, bitKey));
						}
					}
					// * 문을 만난 경우 : 현재 가지고 있는 key와 문을 비교해보고 맞다면 방문한다.
					else if(map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
						int bitDoor = (1 << map[nr][nc] - 'A') & c.key;
						if(bitDoor > 0) {
							isVisited[nr][nc][c.key] = true;
							queue.offer(new Coord(nr, nc, c.key));
						}
					}
					// * 비어있을 경우 : 방문한다. 
					else {
						isVisited[nr][nc][c.key] = true;
						queue.offer(new Coord(nr, nc, c.key));						
					}
				}
			}
			// 03-02. 시간을 증가시킨다.
			time++;
		}
		// 04. 결과를 출력한다.
		if(isEscaped) System.out.println(time);
		else System.out.println(-1);
	}
}
