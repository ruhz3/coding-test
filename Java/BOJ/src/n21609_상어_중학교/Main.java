package n21609_상어_중학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Group{
	List<int[]> elems;	// 그룹 좌표 저장
    int r, c;			// 기준 좌표 저장
    int elemCount;		// 그룹 블록 개수
    int rainbowCount;	// 무지개 블록 개수
    
    public Group(List<int[]> elems, int r, int c, int elemCount, int rainbowCount) {
        super();
        this.elems = elems;
        this.r = r;
        this.c = c;
        this.elemCount = elemCount;
        this.rainbowCount = rainbowCount;
    }
}

public class Main {
    static int N, M;
    static int[][] board;
    static int[][] spare;
    static Group maxGroup;

    static Queue<int[]> queue = new LinkedList<>();
    static boolean[][] isVisited;
    static int[] rowDir = {-1, 0, 1, 0};
    static int[] colDir = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
    	// 00. 입력한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isVisited = new boolean[N][N];
        spare = new int[N][N];
        board = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 01. 게임을 진행한다.
        int score = 0;
        while(true) {
        	setMaxGroup();
        	if(maxGroup == null) break;
        	score += calScore();
        	setGravity();
        	rotateBoard();
        	setGravity();
        }
        // 02. 점수를 출력한다.
        System.out.println(score);
    }
    
    /* 최대 블록 그룹을 갱신하는 함수*/
    private static void setMaxGroup() {
    	// 00. 초기화한다. 
        maxGroup = null;
        for(int i = 0; i < N; i++) {
        	Arrays.fill(isVisited[i], false);
        }
        
        // 02. 좌표를 행->열로 뽑아서, 자연스레 기준 블록을 설정하도록 한다. 
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] <= 0) continue;
                if(isVisited[i][j]) continue;
                
                // 04. 탐색하며, 그룹 원소와 무지개를 따로 모아준다. 
                List<int[]> elems = new LinkedList<>();
                List<int[]> rainbows = new LinkedList<>();
                int mainColor = board[i][j];
                
                // 05. BFS 탐색한다.
                int[] elem = new int[] {i, j};
                queue.offer(elem);
                isVisited[i][j] = true;
                if(board[i][j] == 0) rainbows.add(elem);
                elems.add(elem);

                while(!queue.isEmpty()) {
                    int[] coord = queue.poll();
                    for(int dir = 0; dir < 4; dir++) {
                        int nr = coord[0] + rowDir[dir];
                        int nc = coord[1] + colDir[dir];
                        if(nr < 0 || nr >= N || nc < 0 || nc >= N || isVisited[nr][nc]) continue;
                        // * 무지개 블록이거나 그룹 색상과 같은 블록만 포함시킨다.
                        if(board[nr][nc] < 0 || (board[nr][nc] != 0 && mainColor != board[nr][nc])) continue;
                        
                        int[] e = new int[]{nr, nc};
                        queue.offer(e);
                        isVisited[nr][nc] = true;
                        if(board[nr][nc] == 0) rainbows.add(e);
                        elems.add(e);
                    }
                }
                for(int[] rainbow : rainbows) {
                	isVisited[rainbow[0]][rainbow[1]] = false;
                }
                if(mainColor != 0 && elems.size() >= 2) {
                	Group group = new Group(elems, i, j, elems.size(), rainbows.size());
                	if(!compareGroup(group)) maxGroup = group;
                }
            }
        }
    }
    /* 원소를 -2로 지우고, 점수를 반환하는 함수*/
    private static int calScore() {
    	int score = 0;
    	score += Math.pow(maxGroup.elemCount, 2);
    	for(int[] elem : maxGroup.elems) {
    		board[elem[0]][elem[1]] = -2;
    	}
    	return score;
    }
    /* 원소를 삭제하고, 점수를 반환하는 함수*/
    private static void setGravity() {
    	for(int i = N-2; i>= 0; i--) {
    		for(int j = 0; j < N; j++) {
    			if(board[i][j] == -1) continue;
    			int row = i;
    			while(row < N-1) {
    				if(board[row+1][j] != -2) break;
    				board[row + 1][j] = board[row][j];
    				board[row][j] = -2;
    				row++;
    			}
    		}
    	}
    }
    /* 원소를 반시계 방향으로 회전하는 함수*/
    private static void rotateBoard() {
    	// 00. 가장 바깥 껍질 부터 안 쪽까지 회전 시킨다(d++).
    	for(int d = 0; d < N/2; d++) {
			int lv = N - 2 * d;
			for(int k = 0; k < lv - 1; k++) {
				// 구역 1 : 상행 <- 우열 
				spare[d + 0][d + k] = board[d + k][d + lv-1];
				// 구역 2 : 우열 <- 하행
				spare[d + k][d + lv-1] = board[d + lv-1][d + lv-1-k]; 
				// 구역 3 : 하행 <- 좌열
				spare[d + lv-1][d + lv-1-k] = board[d + lv-1-k][d + 0];
				// 구역 4 : 좌열 <- 상행
				spare[d + lv-1-k][d + 0] = board[d + 0][d + k];
			}
		}
    	// 01. 홀 수 인 경우, 가운데 원소를 복사해준다.
    	if(N%2 != 0) spare[N/2][N/2] = board[N/2][N/2];
    	
    	// 02. 새 배열로 교체해준다.
    	int[][] tmp = spare;
    	spare = board;
    	board = tmp;
    }
    /* maxGroup과 비교했을 때 'maxGroup이 더 큰지'를 반환하는 함수*/
    private static boolean compareGroup(Group group) {
    	if(maxGroup == null) return false;
    	// * 우선순위 : 블록 개수 > 무지개 개수 > 기준 블록 행 > 기준 블록 열
    	if(maxGroup.elemCount != group.elemCount) return maxGroup.elemCount > group.elemCount;
    	if(maxGroup.rainbowCount != group.rainbowCount) return maxGroup.rainbowCount > group.rainbowCount;
    	if(maxGroup.r != group.r) return maxGroup.r > group.r;
    	if(maxGroup.c != group.c) return maxGroup.c > group.c;
    	return true;
    }
}

