package n2531_회전_초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, d, k, c;
	static boolean[] isVisited;
	static int[] sushiTrain;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		isVisited = new boolean[d+1];
		
		sushiTrain = new int[N+k-1];
		for(int i = 0; i < N; i++) {
			sushiTrain[i] = Integer.parseInt(br.readLine());
		}
		for(int i = N, j = 0; i < N + k-1; i++, j++) {
			sushiTrain[i] = sushiTrain[j];
		}
		
		int maxCount = 0;
		for(int i = 0; i < N; i++) {
			Arrays.fill(isVisited, false);
			int count = 0;
			for(int j = 0; j < k; j++) {
				int sushi = sushiTrain[i+j];
				if(!isVisited[sushi]) {
					isVisited[sushi] = true;
					count++;
				}
			}
			if(!isVisited[c]) count++;
			maxCount = Math.max(count, maxCount);
		}
		System.out.println(maxCount);
	}
}
