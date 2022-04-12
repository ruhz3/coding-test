package kakao_2019i_호텔_방_배정;

import java.util.HashMap;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		long k = 10;
		long[] room_number = {1,3,4,1,3,1};
		long[] res = new Solution().solution(k, room_number);
		for(long elem : res) System.out.print(elem);
	}
}

class Solution {
	static HashMap<Long, Long> map = new HashMap<Long, Long>();
	static Stack<Long> stack = new Stack<Long>();
    public long[] solution(long k, long[] room_number) {
        int N = room_number.length;
    	long[] answer = new long[N];
        for(int i = 0; i < N; i++) {
        	answer[i] = findLocation(room_number[i]);
        }
        return answer;
    }
    
    private static long findLocation(long num) {
    	long value = 0;
    	long key = num;
    	while(true) {
    		value = map.getOrDefault(key, (long)0);
        	if(value == 0) {
        		map.put(key, key + 1);
        		break;
        	}
        	stack.add(key);
        	key = value;
    	}
    	while(!stack.isEmpty()) {
    		long k = stack.pop();
    		map.put(k, key);
    	}
    	return key;
    }
}