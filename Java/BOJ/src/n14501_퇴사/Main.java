package n14501_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] costs;
	static int[] rewards;
	static int[] cache;
	static int N;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		init();
		// 01. 최대 보상합을 출력한다.
		System.out.println(getMaxRewardSum(0));
	}
	
	/* 입력*/
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		costs = new int[N];
		rewards = new int[N];
		cache = new int[N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			costs[i] = Integer.parseInt(st.nextToken());
			rewards[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	/* 로직*/
	private static int getMaxRewardSum(int index) {
		// 탈출 조건
		if(index == N) return 0;
		if(cache[index] != 0) return cache[index];
		
		// 포함하는 경우, 안 하는 경우를 비교
		int cost = costs[index];
		int reward = rewards[index];
		int included = 0;
		int excluded = 0;
		if(index + cost <= N) included = getMaxRewardSum(index + cost) + reward;
		if(index + 1 <= N && cost != 1) excluded = getMaxRewardSum(index + 1);
		
		// 메모이제이션
		cache[index] = Math.max(included, excluded);
		return cache[index];
	}
}
