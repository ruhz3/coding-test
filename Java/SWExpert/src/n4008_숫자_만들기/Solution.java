package n4008_숫자_만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS 사용
public class Solution {
	static int[] ops;
	static int[] nums;
	static int[] state;
	static int maxResult, minResult;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			ops = new int[4];
			nums = new int[N];
			state = new int[N-1];
			
			// 00. ops의 개수를 입력한다. 
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 4; i++) {
				ops[i] = Integer.parseInt(st.nextToken());
			}
			
			// 01. nums에 입력 받는다.
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			// 02. dfs 탐색하며, 끝에서 계산을 해준다.
			maxResult = Integer.MIN_VALUE;
			minResult = Integer.MAX_VALUE;
			dfs(0);
			sb.append(maxResult - minResult).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void dfs(int cnt) {
		if(cnt == N-1) {
			cal();
			return;
		}
		for(int op = 0; op < 4; op++) {
			if(ops[op] == 0) continue;
			state[cnt] = op;
			ops[op]--;
			dfs(cnt+1);
			ops[op]++;
		}
	}
	private static void cal() {
		int result = nums[0];
		int idx = 1;
		for(int op : state) {
			if(op == 0) result += nums[idx++];
			else if(op == 1) result -= nums[idx++];
			else if(op == 2) result *= nums[idx++];
			else result /= nums[idx++];
		}
		maxResult = Math.max(maxResult, result);
		minResult = Math.min(minResult, result);
	}
}

// Next Permutation 사용
/*
public class Solution {
	static int[] ops;
	static int[] nums;
	static int N;
	
    public static void main(String[] args) throws NumberFormatException, IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            ops = new int[N-1];
            nums = new int[N];
            
            // 00. ops는 개수가 아니라, 실제 연산자를 위치 시킨다. 
            st = new StringTokenizer(br.readLine(), " ");
            for(int op = 0, idx = 0; op < 4; op++) {
            	int n = Integer.parseInt(st.nextToken());
            	for(int i = 0; i < n; i++) ops[idx++] = op;
            }
            
            // 01. nums에 입력 받는다.
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; i++) {
            	nums[i] = Integer.parseInt(st.nextToken());
            }
            
            // 02. nextPermutation 을 실행하며, 최댓값 최솟값을 갱신해준다. 
            int maxResult = Integer.MIN_VALUE;
            int minResult = Integer.MAX_VALUE;
            do {
            	int result = nums[0];
            	for(int i = 1; i < N; i++) {
            		int op = ops[i-1];
            		int num = nums[i];
            		if(op == 0) result += num;
            		else if(op == 1) result -= num;
            		else if(op == 2) result *= num;
            		else result /= num;
            	}
            	maxResult = Math.max(maxResult, result);
            	minResult = Math.min(minResult, result);
            } while(nextPermutation());
            sb.append(maxResult - minResult).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    private static boolean nextPermutation() {
    	// 00. 꼭대기 지점을 찾는다.
    	int i = N-2;
    	while(i > 0 && ops[i-1] >= ops[i]) --i;

    	// 01. 꼭대기가 맨 앞에 와있다면, 정렬은 끝났다.
    	if(i == 0) return false;
    	
    	// 02. 꼭대기 바로 앞 원소와, 그 뒤의 원소 중 가장 작은 값을 교체한다.
    	int j = N-2;
    	while(ops[i-1] >= ops[j]) --j;
    	swap(i-1, j);
    	
    	// 03. 꼭대기 지점 부터 뒤 원소들을 정렬해준다.
    	while(i < k) {
    		swap(i++, k--);
    	}
    	return true;
    }
    private static void swap(int i, int j) {
    	int tmp = ops[i];
    	ops[i] = ops[j];
    	ops[j] = tmp;
    }
}
*/
