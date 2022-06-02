package kakao_2021i_숫자_문자열과_영단어;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		int res = new Solution().solution("23four5six7");
		System.out.println(res);
	}
}

class Num {
	int value;
	int length;
	public Num(int value, int length) {
		super();
		this.value = value;
		this.length = length;
	}
}

class Solution {
    public int solution(String s) {
    	HashMap<String, Num> map = new HashMap<String, Num>();
    	map.put("ze", new Num(0, 4));
    	map.put("on", new Num(1, 3));
    	map.put("tw", new Num(2, 3));
    	map.put("th", new Num(3, 5));
    	map.put("fo", new Num(4, 4));
    	map.put("fi", new Num(5, 4));
    	map.put("si", new Num(6, 3));
    	map.put("se", new Num(7, 5));
    	map.put("ei", new Num(8, 5));
    	map.put("ni", new Num(9, 4));
    	
        int len = s.length();
        
        StringBuilder number = new StringBuilder();
        int index = 0;
        while(index < len) {
        	if(s.charAt(index) >= 'a' && s.charAt(index) <='z') {
        		String key = s.substring(index, index+2);
        		Num num = map.get(key);
        		number.append(num.value);
        		index += num.length;
        	} else {
        		number.append(s.charAt(index));
        		index++;        		
        	}
        }
        return Integer.parseInt(number.toString());
    }
}