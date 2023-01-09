

public class test {
	public static void main(String[] args) {
		System.out.println(isMatched("slang", "sl..", 2));
	}
    private static boolean isMatched(String slang, String word, int k) {
    	int len = word.length();
    	System.out.println("S : " + slang + ", " + "W : " + word);
    	for(int i = 0; i < len ;i++) {
    		if(word.charAt(i) == '.') {
    			if(len == 1 && slang.length() <= k) return true;
    			for(int j = 1; j <= k && i+j < len; j++) {
    				if(isMatched(slang.substring(i+j), word.substring(i+j), k)) {
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
}
