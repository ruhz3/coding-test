package practice_heap_이중우선순위큐;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// static String[] operations = {"I 16","D 1"};
	// static String[] operations = {"I 7","I 5","I -5","D -1"};
	// static String[] operations = {"I 3", "I 1", "I 2", "I 4", "I 5", "I 0", "D 1", "D 1", "D -1", "D -1", "D 1"};
	static String[] operations = {"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"};
	public static void main(String[] args) {
		int[] result = new Solution().solution(operations);
		for(int elem : result) {
			System.out.print(elem + " ");
		}
	}
}

class Solution {
    public int[] solution(String[] operations) {
    	PriorityQueue<Integer> ascHeap = new PriorityQueue<>();
    	PriorityQueue<Integer> descHeap = new PriorityQueue<>(Collections.reverseOrder());
    	int count = 0;
    	for(String operation : operations) {
    		StringTokenizer st = new StringTokenizer(operation, " ");
    		char op = st.nextToken().charAt(0);
    		int num = Integer.parseInt(st.nextToken());
    		if(op == 'I') {
    			ascHeap.offer(num);
    			descHeap.offer(num);
    			count++;
    		} else if(count > 0) {
    			if(num == -1) ascHeap.poll();
    			else descHeap.poll();
    			count--;
    		}
    		if(count == 0) {
    			ascHeap.clear();
    			descHeap.clear();
    		}
    	}
    	int[] answer = {0, 0};
    	if(count > 0) {
    		answer[0] = descHeap.poll();
    		answer[1] = ascHeap.poll();    		
    	}
        return answer;
    }
}