package LINE_01;

import java.util.HashMap;
import java.util.Map;

public class Main {
	static int[][] queries = {{2, 10}, {7, 1}, {2, 5}, {2, 9}, {7, 32}};
	public static void main(String[] args) {
		int res = new Solution().solution(queries);
		System.out.println(res);
	}
}

class Solution {
	Map<Integer, int[]> map = new HashMap<>();
	
    public int solution(int[][] queries) {
    	int count = 0;
    	for(int[] query : queries) {
    		int id = query[0];
    		int addNum = query[1];
    		
    		// 배열을 처음 생성할 때
    		if(!map.containsKey(id)) {
    			int arrSize = 1;
    			while(addNum > arrSize) {
    				arrSize *= 2;
    			}
    			map.put(id, new int[] {addNum, arrSize});
    		}
    		
    		// 이미 배열이 존재할 때
    		else {
    			int[] arr = map.get(id);;
    			int elemNum = arr[0] + addNum;
    			int arrSize = arr[1];
    			boolean isExpanded = false;
    			while(elemNum > arrSize ) {
    				isExpanded = true;
    				arrSize *= 2;
    			}
    			if(isExpanded) count += arr[0];
    			arr[0] = elemNum;
    			arr[1] = arrSize;
    		}
    	}
    	return count;
    }
}