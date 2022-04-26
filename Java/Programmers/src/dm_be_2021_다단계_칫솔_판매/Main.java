package dm_be_2021_다단계_칫솔_판매;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = {"young", "john", "tod", "emily", "mary"};
		int[] amount = {12, 4, 2, 5, 10};
		
		int[] res = new Solution().solution(enroll, referral, seller, amount);
		for(int elem : res) {
			System.out.print(elem + " ");
		}
	}
}

class Member {
	Member boss; 
	int wallet; 
	
	public void earn(int amount) {
		// 10%를 떼고, (있다면)상관에게 준다.
		int fee = amount / 10;
		if(boss != null && fee > 0) boss.earn(fee);
		wallet += amount - fee;
	}
}

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        HashMap<String, Member> map = new HashMap<String, Member>();
        
        // 00. 다단계 피라미드를 완성한다.
        int N = enroll.length;
        for(int i = 0; i < N; i++) {
        	map.put(enroll[i], new Member());        	
        }
        for(int i = 0; i < N; i++) {
        	if(referral[i].equals("-")) continue;
        	map.get(enroll[i]).boss = map.get(referral[i]);
        }
        
        // 01. 판매한다.
        int M = seller.length;
        for(int i = 0; i < M; i++) {
        	map.get(seller[i]).earn(amount[i] * 100);
        }
        
        // 02. 이익금을 출력한다.
        int[] answer = new int[N];
        for(int i = 0; i < N; i++) {
        	answer[i] = map.get(enroll[i]).wallet;
        }
        
        return answer;
    }
}