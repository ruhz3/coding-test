package LINE_02;

import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		int k = 2;
		String[] dic = {"slang", "badword"};
		String chat = "badword ab.cd bad.ord .word sl.. bad.word";
		String res = new Solution().solution(k, dic, chat);
		System.out.println(res);
	}
}

class Solution {	
    public String solution(int k, String[] dic, String chat) {
        StringTokenizer st = new StringTokenizer(chat, " ");
        StringBuilder sb = new StringBuilder();
        
        while(st.hasMoreTokens()) {
        	String word = st.nextToken();
        	if(isSlang(dic, word, k)) {
        		word = maskedString(word);
        	}
        	sb.append(word).append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    
    /* 사전을 뒤져보는 함수 */
    private boolean isSlang(String[] dic, String word, int k) {
    	for(String slang : dic) {
    		if(isMatched(slang, word, k)) return true;
    	}
    	return false;
    }
    /* 사전의 단어와 문장의 단어를 비교하는 함수*/
    private boolean isMatched(String slang, String word, int k) {
    	int wordLen = word.length();
    	int slangLen = slang.length();
    	if(slangLen < wordLen) return false;
    	
    	for(int i = 0; i < wordLen; i++) {
    		// 만약 '.'을 만났다면, 
    		if(word.charAt(i) == '.') {
    			// 지금 '.'이 마지막 글자인데, slang이 모두 대체 된다면 Matched!
    			if(i == wordLen-1) {
    				if(slangLen-1 <= i+k) return true;
    				else return false;
    			}
    			
    			// '.'이 대체할 만큼 떼서, slang을 뗴어버리고 비교한다.
    			for(int j = 0; j < k && i+j < slangLen; j++) {
    				if(isMatched(slang.substring(i+j), word.substring(i+1), k)) {
    					return true;
    				}
    			}
    			return false;
    		} else if(word.charAt(i) != slang.charAt(i)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /* 마스크 된 스트링을 반환하는 함수*/
    private String maskedString(String word) {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0, len = word.length(); i < len; i++) {
    		sb.append("#");
    	}
    	return sb.toString();
    }
}