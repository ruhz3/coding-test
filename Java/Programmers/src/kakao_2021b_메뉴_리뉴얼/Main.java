package kakao_2021b_메뉴_리뉴얼;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static String[][] orders = {
		{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
		{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},
		{"XYZ", "XWY", "WXA"}
	};
	static int[][] courses = {
		{2, 3, 4},
		{2, 3, 5},
		{2, 3, 4}
	};
	public static void main(String[] args) {		
			String[] results = new Solution().solution(orders[1], courses[1]);
			for(String result : results) System.out.print(result + " ");
			System.out.println();
	}
}

class Elem implements Comparable<Elem>{
	String key;
	int count;
	public Elem(String key, int count) {
		super();
		this.key = key;
		this.count = count;
	}
	@Override
	public int compareTo(Elem o) {
		return o.count - count;
	}
}

class Solution {
	static Map<String, Integer> map;
	static int maxCount;
	static int K;
	
    public String[] solution(String[] orders, int[] course) {
    	List<String> answer = new ArrayList<>();
    	map = new HashMap<>();
        for(int i = 0; i < course.length; i++) {
        // 00. 조합 개수가 달라지면 변수를 초기화한다.
        	map.clear();
        	K = course[i];
        	maxCount = 0;
        // 01. 주문을 정렬하고 조합별 수를 세되, 최대 개수를 찾는다.
        	for(int j = 0, len = orders.length; j < len; j++) {
        		char[] order = orders[j].toCharArray();
        		Arrays.sort(order);
        		countAndGetMax(order, "", -1, 0);
        	}
        // 02. 최대 개수에 해당하는 조합을 가져와 answer에 담는다.
        	if(maxCount > 1) {
        		List<String> keyList = new ArrayList<>(map.keySet());
        		for(String key : keyList) {
        			if(map.get(key) == maxCount) answer.add(key);
        		}        		
        	}
        }
        // 03. answer를 사전순 정렬하고, String 배열에 담는다.
        Collections.sort(answer);
        String[] result = new String[answer.size()];
        answer.toArray(result);
        return result;
    }
    private void countAndGetMax(char[] order, String key, int index, int count) {
    	if(count == K) {
    		int value = map.getOrDefault(key, 0) + 1;
    		if(value > maxCount) maxCount = value;
    		map.put(key, value);
    		return;
    	}
    	for(int i = index + 1; i < order.length; i++) {
    		countAndGetMax(order, key + order[i], i, count+1);
    	}
    }
}