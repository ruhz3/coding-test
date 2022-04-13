package kakao_2019i_징검다리_건너기;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		int res = new Solution().solution(stones, k);
		System.out.println(res);
	}
}

class Solution { 
    public int solution(int[] stones, int k) {
    	int N = stones.length;
    	
    	PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(Comparator.reverseOrder());
    	for(int i = 0; i < k; i++) {
    		pQueue.offer(stones[i]);
    	}
    	int minMaxValue = Integer.MAX_VALUE;
    	for(int i = 0; i < N - k; i++) {
    		pQueue.remove(stones[i]);
    		pQueue.offer(stones[i+k]);
    		minMaxValue = Math.min(minMaxValue, pQueue.peek());
    	}
        return minMaxValue;
    }
}