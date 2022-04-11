package kakao_2019i_튜플;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
		new Solution().solution(s);
	}
}

class Node implements Comparable<Node>{
	int value;
	int count;
	public Node(int value, int count) {
		super();
		this.value = value;
		this.count = count;
	}
	@Override
	public int compareTo(Node o) {
		return o.count - count;
	}
}

class Solution {
	public int[] solution(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Node> list = new ArrayList<>();
		
		s = s.replace("{", "");
		s = s.replace("}", "");
        String[] ls = s.split(",");
        
        for(String elem : ls) {
			int key = Integer.parseInt(elem);
			int value = map.getOrDefault(key, -1);
			if(value == -1) {
				map.put(key, list.size());
				list.add(new Node(key, 1));
			} else {
				list.get(value).count++;
			}
		}
        Collections.sort(list);
        int N = list.size();
        int[] answer = new int[N];
        for(int i = 0; i < N; i++) {
        	answer[i] = list.get(i).value;
        }
        return answer;
    }
}
