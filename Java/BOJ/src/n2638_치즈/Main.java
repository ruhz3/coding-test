package n2638_치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Coord{
	int r, c;
	public Coord(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static boolean[][] isVisited;
    static int[][] map;
    static int N, M;
    // 상, 우, 하, 좌
    static Queue<Coord> queue = new LinkedList<>();
    static int[] rowDir = {-1, 0, 1, 0};
    static int[] colDir = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while(true){
            queue.clear();
            for(int i = 0; i < N; i++) {
            	Arrays.fill(isVisited[i], false);
            }
            // 00. 바깥 공기를 표시한다.
            queue.add(new Coord(0, 0));
            map[0][0] = -1;
            while (!queue.isEmpty()) {
                Coord c = queue.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nr = c.r + rowDir[dir];
                    int nc = c.c + colDir[dir];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if(isVisited[nr][nc] || map[nr][nc] == 1) continue;

                    isVisited[nr][nc] = true;
                    map[nr][nc] = -1;
                    queue.offer(new Coord(nr, nc));
                }
            }
            // 01. 바깥 공기를 두 면 이상 맞닿고 있다면 녹는다.
            boolean isDone = true;
            for (int i = 1; i < N-1; i++) {
                for (int j = 1; j < M-1; j++) {
                    if(map[i][j] == 1){
                        int cnt = 0;
                        for (int dir = 0; dir < 4; dir++) {
                            int nr = i + rowDir[dir];
                            int nc = j + colDir[dir];
                            if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                            if(map[nr][nc] == -1) cnt++;
                        }
                        if (cnt >= 2) {
                        	isDone = false;
                            map[i][j] = 0;
                        }
                    }
                }
            }
            // 02. 아무것도 녹일 것이 없었다면, 종료한다.
            if(isDone) break;
            time ++;
        }
        System.out.println(time);
    }
}
