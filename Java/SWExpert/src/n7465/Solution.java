package n7465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 7465_창용 마을 무리의 개수
 * 메모리 :	26,316 kb
 * 실행시간 :	137 ms
 */

public class Solution {
	static int[][] town;
	static int[] group;
	static int N, M;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 01. 인접리스트에 입력한다.
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			town = new int[N+1][N+1];
			group = new int[N+1];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				town[a][b] = 1;
				town[b][a] = 1;
			}
			
			// 02. 서로 연결해준다.
			int groupNum = 1;
			while(true) {
				int person = 0;
				for(int i = 1; i <= N; i++)
					if(group[i] == 0) {
						person = i;
						break;
					}
				if(person == 0) break;
				group[person] = groupNum;
				makeGroup(person, groupNum);
				groupNum++;
			}
			// 03. 출력한다.
			result.append("#").append(tc).append(" ").append(groupNum-1).append("\n");
		}
		System.out.println(result.toString());
	}
	
	private static void makeGroup(int person, int groupNum) {
		// 현재 person에 대하여, i와 연결되어 있고 i가 group이 없다면,
		// group에 껴주고 친구들을 불러오라고 한다.
		for(int i = 1; i <= N; i++) {
			if(town[person][i] == 1 && group[i] == 0) {
				group[i] = groupNum;
				makeGroup(i, groupNum);
			}
		}
	}
}
