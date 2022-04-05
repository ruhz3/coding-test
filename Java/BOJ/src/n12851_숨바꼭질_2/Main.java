package n12851_숨바꼭질_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int MAX = 100000;
	
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int[] visitTime = new int[MAX+1];
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 01. 시작점을 큐에 넣어준다.
		visitTime[N] = 1;
		queue.add(N);
		
		// 02. BFS 탐색하되, 동일 level에서 중복 방문은 허용한다. 
		int timeCount = 0;
		int methodCount = 0;
		while(!queue.isEmpty()) {
			int len = queue.size();
			boolean isDone = false;
			methodCount = 0;
			timeCount++;
			
			while(len > 0) {
				int n = queue.poll();
				if(n == K) break;
				for(int i = 0; i < 3; i++) {
					int key = 0;
					if(i == 0) key = n-1;
					else if(i == 1) key = n+1;
					else if(i == 2) key = 2*n;
					
					if(key == K) {
						isDone = true;
						methodCount++;
						continue;
					}
					if(key < 0 || key > MAX) continue;
					if((visitTime[key] == 0 || visitTime[key] == visitTime[n] + 1)) {
						visitTime[key] = visitTime[n] + 1;
						queue.offer(key);					
					}
				}
				len--;
			}
			if(isDone) break;
		}
		
		// 03. 출력한다.
		System.out.println(new StringBuilder()
				.append(timeCount)
				.append("\n")
				.append(methodCount).toString());
	}
}
