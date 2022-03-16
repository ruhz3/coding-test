package n12865_평범한_배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] things;
	static int[][] cache;
	
	public static void main(String[] args) throws IOException{
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		things = new int[N][2];
		cache = new int[N][K+1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			things[i][0] = Integer.parseInt(st.nextToken());  // 무게
			things[i][1] = Integer.parseInt(st.nextToken());  // 가치
			Arrays.fill(cache[i], -1);
		}
		System.out.println(pack(0, K));
	}
	
	private static int pack(int idx, int available) {
		if(idx >= N) return 0;
		if(cache[idx][available] != -1)
			return cache[idx][available];
		
		int ret = pack(idx+1, available);
		if(things[idx][0] <= available) {
			ret = Math.max(ret, pack(idx+1, available-things[idx][0]) + things[idx][1]);
		}
		cache[idx][available] = ret;
		return ret; 
	}
}
