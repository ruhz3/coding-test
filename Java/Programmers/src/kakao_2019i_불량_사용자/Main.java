package kakao_2019i_불량_사용자;

import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"*rodo", "*rodo", "******"};
		int res = new Solution().solution(user_id, banned_id);
		System.out.println(res);
	}
}

class Solution {
	Set<Integer> set = new HashSet<>();
	String[] users;
	String[] bans;
	
    public int solution(String[] user_id, String[] banned_id) {
        users = user_id;
        bans = banned_id;
        dfs(0, 0);
        return set.size();
    }
    
    private void dfs(int bannedIndex, int flag) {
    	if(bannedIndex == bans.length) {
    		set.add(flag);
    		return;
    	}
    	String pattern = bans[bannedIndex];
    	for(int i = 0, userLen = users.length; i < userLen; i++) {
    		if((flag & 1 << i) == 0 && matches(users[i], pattern)) {
    			dfs(bannedIndex + 1, flag | 1 << i);
    		}
    	}
    }
    private boolean matches(String text, String pattern) {
    	if(text.length() != pattern.length()) return false;
    	int len = text.length();
    	for(int i = 0; i < len; i++) {
    		if(pattern.charAt(i) != '*' && text.charAt(i) != pattern.charAt(i)) return false;
    	}
    	return true;
    }
}