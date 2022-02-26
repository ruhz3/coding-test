package n1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] check = new boolean[200001];
		
		queue.offer(N);
		check[N] = false;
		int count = 0;
	OUTER: while(!queue.isEmpty()) {
			int len = queue.size();
			for(int i = 0; i < len; i++) {
				int num = queue.poll();
				if(num == K) break OUTER;
				
				if(num < K && !check[2 * num]) {
					check[2*num] = true;
					queue.offer(2 * num);
				}
				if(num > 0 && !check[num - 1]) {
					check[num - 1] = true;
					queue.offer(num - 1);
				}
				if(num < K && !check[num + 1]) {
					check[num + 1] = true;
					queue.offer(num + 1);
				}
			}
			count++;
		}
		System.out.println(count);
	}
}
