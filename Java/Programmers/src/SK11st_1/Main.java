package SK11st_1;

public class Main {
	public static void main(String[] args) {
		int[] A = {13, 7, 2, 8, 3};
		int res = new Solution().solution(A);
		System.out.println(res);
	}
}

class Solution {
    public int solution(int[] A) {
    	// 00. 최댓값을 찾는다.
    	int N = A.length;
    	int maxIdx = 0;
    	for(int i = 0; i < N; i++) {
    		if(A[maxIdx] < A[i]) {
    			maxIdx = i;
    		}
    	}
    	// 01. 자릿수별로 최대 겹치는 값을 계산한다.
    	int maxCount = 0;
    	while(A[maxIdx] > 0) {
    		int count = 0;
    		for(int i = 0; i < N; i++) {
    			if(A[i] == 0) continue;
    			if(A[i] % 2 == 1) count++;
    			A[i] /= 2;
    		}
    		maxCount = Math.max(maxCount, count);
    	}
    	return maxCount;
    }
}
