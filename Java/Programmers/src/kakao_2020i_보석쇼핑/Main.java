package kakao_2020i_보석쇼핑;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		int[] res = new Solution().solution(gems);
		for(int elem : res) {
			System.out.println(elem);
		}
	}
}

class Solution {
    public int[] solution(String[] gems) {
    	int[] answer = new int[2];
    	HashMap<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        
        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList(gems));
        int K = set.size();
        int N = gems.length;
        
        int gap = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;

        for (int i = 0; i < N; i++) {
        	int key = 0;
        	if(map.containsKey(gems[i])) key = map.get(gems[i]);
            map.put(gems[i], key + 1);
            queue.add(gems[i]);
            
            String jewerly = queue.peek();
            while (map.get(jewerly) > 1) {
                map.put(jewerly, map.get(jewerly) - 1);
                jewerly = queue.poll();
                end++;
            }
            if (map.size() == K && gap > i - end) {
                gap = i - end;
                start = end + 1;
            }
        }
        answer[0] = start;
        answer[1] = start + gap;
        return answer;
    }
}


