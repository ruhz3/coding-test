package n6593_상범_빌딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Coord {
	int l, r, c;

	public Coord(int l, int r, int c) {
		super();
		this.l = l;
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static boolean[][][] building;
	static int L, R, C;
	// 상, 우, 하, 좌, 위 , 아래
	static int[] dl = {0, 0, 0, 0, 1, -1};
	static int[] dr = {-1, 0, 1, 0, 0, 0};
	static int[] dc = {0, 1, 0, -1, 0, 0};
	
	static Queue<Coord> queue = new LinkedList<Coord>();
	static boolean[][][] isVisited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		while(true) {
			// 00. 입력한다(변수, 자료형).
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L == 0 && R == 0 && C == 0) break;
			building = new boolean[L][R][C];
			isVisited = new boolean[L][R][C];
			queue.clear();
			
			// 01. 입력한다(빌딩). 
			int sl = 0, sr = 0, sc = 0;
			int el = 0, er = 0, ec = 0;
			for(int l = 0; l < L; l++) {
				for(int r = 0; r < R; r++) {
					String in = br.readLine();
					for(int c = 0; c < C; c++) {
						switch(in.charAt(c)) {
						case 'S':
							sl = l; sr = r; sc = c;
							building[l][r][c] = true;
							break;
						case 'E':
							el = l; er = r; ec = c;
							building[l][r][c] = true;
							break;
						case '#':
							building[l][r][c] = false;
							break;
						case '.':
							building[l][r][c] = true;
							break;
						}
					}
				}
				br.readLine();
			}
			// 02. BFS 탐색한다.
			isVisited[sl][sr][sc] = true;
			queue.offer(new Coord(sl, sr, sc));
			int time = 0;
			boolean isTrapped = true;
		OUTER : while(!queue.isEmpty()) {
				int len = queue.size();
				for(int i = 0; i < len; i++) {
					Coord c = queue.poll();
					
					for(int dir = 0; dir < 6; dir++) {
						int nl = c.l + dl[dir];
						int nr = c.r + dr[dir];
						int nc = c.c + dc[dir];
						if(nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
						if(isVisited[nl][nr][nc] || !building[nl][nr][nc]) continue;
						if(nl == el && nr == er && nc == ec) {
							isTrapped = false;
							break OUTER;
						}
						
						isVisited[nl][nr][nc] = true;
						queue.offer(new Coord(nl, nr, nc));
					}
				}
				time++;
			}
			// 03. 출력한다.
			if(isTrapped) result.append("Trapped!").append("\n");
			else result.append("Escaped in ").append(time + 1).append(" minute(s).").append("\n");;
		}
		System.out.println(result.toString());
	}
}
