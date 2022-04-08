package n15685_드래곤_커브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
	static LinkedList<int[]>[] dragons;
	static boolean[][] map;
	static int[] rotates;
	
	static int[] rowDir = {1, 0, -1, 0};
	static int[] colDir = {0, -1, 0, 1};
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		dragons = new LinkedList[N];
		rotates = new int[N];
		map = new boolean[100][100];
		
		for(int i = 0; i < N; i++) {
			dragons[i] = new LinkedList<int[]>();
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			dragons[i].add(new int[]{x, y});
			dragons[i].add(new int[]{x + rowDir[dir], y + colDir[dir]});
			rotates[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			LinkedList<int[]> dragon = dragons[i];
			for(int loop = 0; loop < rotates[i]; loop++) {
				int len = dragon.size();
				for(int j = 0; j < len; j++) {
					int[] O = dragon.getLast();
					for(int k = len-2; k >= 0; k--) {
						int[] A = dragon.get(k);
						int[] B = new int[2];
						B[0] = O[0] + (A[1] - O[1]);
						B[1] = O[1] - (A[0] - O[0]);
						dragon.add(B);
					}
				}		
			}
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println(dragons[i]);
		}
		
		for(int i = 0; i < N; i++) {
			for(int[] elem : dragons[i]) {
				map[elem[0]][elem[1]] = true;
			}
		}
		
		int count = 0;
		for(int i = 0; i < 100 - 1; i++) {
			for(int j = 0; j < 100 - 1; j++)
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1])
					count++;
		}
		
		System.out.println(count);
	}
}
