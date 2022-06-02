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
	
	private static int pack(int index, int available) {
		if(index >= N) return 0;
		if(cache[index][available] != -1)
			return cache[index][available];
		
		int ret = pack(index+1, available);
		if(things[index][0] <= available) {
			ret = Math.max(ret, pack(index+1, available-things[index][0]) + things[index][1]);
		}
		cache[index][available] = ret;
		return ret; 
	}
}
