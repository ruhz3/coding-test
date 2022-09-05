package n1912_연속합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int[] cache;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		cache = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int maxValue = cache[0] = arr[0];
		for(int i = 1; i < N; i++) {
			if(cache[i-1] < 0) cache[i] = arr[i];
			else cache[i] = cache[i-1] + arr[i]; 
			maxValue = Math.max(maxValue, cache[i]);
		}
		System.out.println(maxValue);
	}

}
