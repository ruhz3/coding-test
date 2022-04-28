package n5557_1학년;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] nums;
	static long[][] cache;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N-1];
		cache = new long[N-1][901];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 01. 마지막 입력은 따로 저장하고, 전체 합을 구한다.
		int sum = 0;
		for(int i = 0; i < N-1; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			sum += nums[i];
			Arrays.fill(cache[i], -1);
		}
		K = Integer.parseInt(st.nextToken());
		
		// 02. dfs 탐색(메모이제이션)하여 출력한다.
		System.out.println(dfs(0, nums[0], sum));
	}
	
	private static long dfs(int index, int cum, int sum) {
		// * index : 현재 방문 중인 인덱스
		// * cum : 0 ~ index 까지의 계산 결과
		// * sum : 앞으로 방문해야할 원소들의 합
		if(cum < 0 || cum > 20 || cum + sum < K || cum - sum > K) return 0;
		if(cache[index][cum] != -1) return cache[index][cum];
		if(index == N-2) {
			if(cum == K) return 1;
			else return 0;
		}
		long res = 0;
		int num = nums[index+1];
		res += dfs(index+1, cum+num, sum-num);
		res += dfs(index+1, cum-num, sum-num);
		cache[index][cum] = res;
		return res;
	}
}