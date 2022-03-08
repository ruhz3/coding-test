package n1859_백만_장자_프로젝트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 1859_백만 장자 프로젝트
 * 메모리 :	139,140 kb
 * 실행시간 :	473 ms
 */

class Timing {
	int time;
	int price;
	public Timing(int time, int price) {
		this.time = time;
		this.price = price;
	}
}

public class Solution {
	static Stack<Timing> stack = new Stack<Timing>();
	static int[] prices;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 00. 입력한다.
			int N = Integer.parseInt(br.readLine());
			prices = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			// 01. 매도 타이밍을 뒤에서 부터 계산한다.
			int maxPrice = 0;
			boolean isPeak = true;
			for(int i = N-1; i >= 0; i--) {
				if(prices[i] >= maxPrice) {
					maxPrice = prices[i];
					isPeak = true;
					continue;
				}
				if(isPeak && prices[i] < maxPrice){
					stack.add(new Timing(i+1, prices[i+1]));
					isPeak = false;
					continue;
				}
			}
			// 02. 앞에서 부터 매수해서 매도 타이밍에 판다.
			if(stack.size() != 0) {
				Timing t = stack.pop();
				int timingIdx = t.time;
				int timingPrice = t.price;
				long sum = 0;
				for(int i = 0; i < N; i++) {
					if(i == timingIdx) {
						if(stack.size() == 0) break;
						t = stack.pop();
						timingIdx = t.time;
						timingPrice = t.price;
						continue;
					}
					if(prices[i] < timingPrice) {
						sum += timingPrice - prices[i];
					}
				}
				result.append("#").append(tc).append(" ").append(sum).append("\n");				
			} else {
				result.append("#").append(tc).append(" ").append(0).append("\n");
			}
		}
		System.out.println(result.toString());
	}
}
