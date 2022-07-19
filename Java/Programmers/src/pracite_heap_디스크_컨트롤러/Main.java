package pracite_heap_디스크_컨트롤러;

import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) {
		new Solution().solution(new int[][] {{0, 3}, {1, 9}, {2, 6}});
	}
}

class Solution {
    public int solution(int[][] jobs) {
    	PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();

    	int answer = 0;
        int count = 0;
        int now = 0, prev = -1;
        while(count < jobs.length){
            for(int[] job : jobs){
                if(prev < job[0] && job[0] <= now){
                    answer += (now - job[0]);
                    pQueue.offer(job[1]);
                }
            }
            int size = pQueue.size();
            if(size > 0){
            	int d = pQueue.poll();
                answer += size * d;
                prev = now;
                now += d;
                count++;
            } else {
                now++;
            }
        }
        return (int)answer / jobs.length;
    }
}