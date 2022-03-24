package TossBank_01;


public class Main {
	static int k1 = 3;
	static int m1 = 50000;
	static String[] names1= {"msLEE", "jsKim", "jsKIM", "jskiM", "jskim", "John", "john", "John", "msLEE", "msLEE", "jsKIM", "roy"};
	static int[] amounts1 = {950, 52524, 1400, 6055, 10000, 4512, 512, 52000, 9000, 49000, 1400, 50000};
	
	static int k2 = 2;
	static int m2 = 3451;
	static String[] names2 = {"abcd", "aBCd", "jsKIM", "rrr", "rrr"};
	static int[] amounts2 = {950, 1000, 1400, 4000, 10000};
	
	public static void main(String[] args) {
		int result = new Solution().solution(k2, m2, names2, amounts2);
		System.out.println(result);
	}
}

class Solution {
    public int solution(int k, int m, String[] names, int[] amounts) {
        int answer = 0;
        int N = names.length;
        int cnt = 1;
        String prev = "";
        for(int i = 0; i < N; i++) {
        	String name = names[i].toUpperCase();
        	int amount = amounts[i];
        	
        	if(prev.equals(name)) cnt++;
        	else cnt = 1;
        	
        	if(cnt >= k || amount >= m) answer++;
        	prev = name;
        }
        return answer;
    }
    
}
