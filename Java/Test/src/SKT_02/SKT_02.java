package SKT_02;

public class SKT_02 {
	public static void main(String[] args) {
		new Solution().solution(6, true);
	}
}

class Solution {
    public int[][] solution(int n, boolean clockwise) {
    	int[][] answer = new int[n][n];
    	int level = 0;
    	int startNum = 1;
    	int N = n;
    	if(clockwise) {
    		while(N > 0) {
        		// 우
        		for(int i = level, sn = startNum; i < n-1-level; i++) {
        			answer[level][i] = sn++;
        		}
        		// 하
        		for(int i = level, sn = startNum; i < n-1-level; i++) {
        			answer[i][n-1-level] = sn++;
        		}
        		// 좌
        		for(int i = n-1-level, sn = startNum; i > level; i--) {
        			answer[n-1-level][i] = sn++;
        		}
        		// 상
        		for(int i = n-1-level, sn = startNum; i > level; i--) {
        			answer[i][level] = sn++;
        		}
        		level++;
        		startNum += N-1;
        		N -= 2;
        	}
    	} else {
    		while(N > 0) {
    			// 하
        		for(int i = level, sn = startNum; i < n-1-level; i++) {
        			answer[i][level] = sn++;
        		}
    			// 우
        		for(int i = level, sn = startNum; i < n-1-level; i++) {
        			answer[n-1-level][i] = sn++;
        		}
        		// 상
        		for(int i = n-1-level, sn = startNum; i > level; i--) {
        			answer[i][n-1-level] = sn++;
        		}
        		// 좌
        		for(int i = n-1-level, sn = startNum; i > level; i--) {
        			answer[level][i] = sn++;
        		}
        		
        		level++;
        		startNum += N-1;
        		N -= 2;
        	}
    	}
    	if(n % 2 != 0) answer[n/2][n/2] = startNum;
        return answer;
    }
}