package SKT_02_01;

import java.util.ArrayList;
import java.util.Collections;

public class SKT_02_01 {
	static String[] case1 = {"pencil", "cilicon", "contrabase", "picturelist"};
	static String[] case2 = {"abcdeabcd","cdabe","abce","bcdeab"};
	public static void main(String[] args) {
		String[] res = new Solution().solution(case2);
		for(int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
	}
}

class Solution {
    public String[] solution(String[] goods) {
    	int len = goods.length;
    	String[] answer = new String[len];
        System.out.println(len);
        for(int i = 0; i < len; i++) {
        	// 00. item의 고유 검색어를 찾는다
        	ArrayList<String> list = new ArrayList<String>();
        	String item = goods[i];
        	int itemLen = item.length();
        	
        	// 01. 길이를 하나씩 늘려가며 부분 문자열을 생성한다.
        	String substr;
        	boolean isDone = false;
        	for(int l = 0; l <= itemLen; l++) {
        		for(int idx = 0; idx < itemLen; idx++) {
        			if(idx + l >= itemLen) break;
        			substr = item.substring(idx, idx+l+1);
        			if(list.contains(substr)) continue;
        			// 02. 해당 부분 문자열이 다른 단어에 있는지 확인한다.
        			boolean isUnique = true;
        			for(int j = 0; j < len; j++) {
        				if(i == j) continue;
        				if(goods[j].contains(substr)) {
        					isUnique = false;
        					break;
        				}
        			}
        			// 03. 없다면, 찾은 고유 검색어를 리스트에 추가한다. 
        			if(isUnique) {
        				isDone = true;
        				list.add(substr);
        			}
        		}
        		// 04. 단어를 찾았다면, 해당 길이까지만 찾고 다음 단어로 넘어간다. 
        		if(isDone) break;
        	}
        	
        	// 05. 찾은 고유 검색어를 적절히 편집하여 answer에 넣어준다. 
        	if(list.isEmpty()) {
        		answer[i] = "None";
        	} else {
        		Collections.sort(list);
        		StringBuilder result = new StringBuilder();
        		for(String elem : list) result.append(elem).append(" ");
        		answer[i] = result.deleteCharAt(result.length()-1).toString();
        	}
        }
        return answer;
    }
}