package kakao_2020i_수식_최대화;

public class Main {
	static String expression = "2-990-5+2";
	public static void main(String[] args) {
		long res = new Solution().solution(expression);
		System.out.println(res);
	}
}

class Solution {
	int[] order = new int[3];
	String[] ops = {"\\*", "\\+", "\\-"}; 
	
    public long solution(String expression) {  
        long maxResult = 0;
        for(int i = 0; i < 3; i++) {
        	for(int j = 0; j < 3; j++) {
        		if(i == j) continue;
        		for(int k = 0; k < 3; k++) {
        			if(j == k || k == i) continue;
        			order[0] = i;
        			order[1] = j;
        			order[2] = k;
        			maxResult = Math.max(maxResult, Math.abs(calculate(expression, 0)));
        		}
        	}
        }
        return maxResult;
    }
    
    private long calculate(String expression, int count) {
    	if(count == 3) return Long.parseLong(expression);
    	int idx = order[count];
    	String[] list = expression.split(ops[idx]);
    	
    	long res = calculate(list[0], count+1);
    	for(int i = 1; i < list.length; i++) {
    		if(ops[idx].equals("\\*")) {
    			res *= calculate(list[i], count+1);
    		} else if(ops[idx].equals("\\+")) {
    			res += calculate(list[i], count+1);
    		} else if(ops[idx].equals("\\-")) {
    			res -= calculate(list[i], count+1);
    		}
    	}
    	return res;
    }
}