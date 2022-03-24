package TossBank_02;

import TossBank_02.Solution;

public class Main {
	static int[] a1 = {1, 6};
	static int[] b1 = {3, 5};
	static int duration1 = 1;
	static int distance1 = 2;
	
	static int[] a2 = {1, 6};
	static int[] b2 = {2, 5};
	static int duration2 = 1;
	static int distance2 = 3;
	 
	public static void main(String[] args) {
		int result = new Solution().solution(a1, b1, duration1, distance1);
		System.out.println(result);
	}
}

class Solution {
    public int solution(int[] a, int[] b, int duration, int distance) {
        int answer = 101;
        // 시간 남으면 분할 정복으로 리팩토링 할 것
        for(int point = 0; point <= distance; point++) {
        	int goTime_A = a[0] + point;
        	int goTime_B = b[0] + distance-point;
        	
        	int helloTime = Math.max(goTime_A, goTime_B);
        	int byeTime = helloTime + duration;
        	
        	int backTime_A = byeTime + point;
        	int backTime_B = byeTime + distance-point;
        	if(backTime_A <= a[1] && backTime_B <= b[1]) {
        		answer = Math.min(answer, helloTime);
        	}
        }
        if(answer == 101) return -1;
        return answer;
    }
    
}
