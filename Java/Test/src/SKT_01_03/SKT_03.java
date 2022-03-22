package SKT_01_03;

public class SKT_03 {
	public static void main(String[] args) {
		System.out.println(new Solution().solution(51, 37, new int[][] {{17, 19}}));
	}
}

class Solution {
	int[][] cache; 
    public int solution(int width, int height, int[][] diagonals) {
    	cache = new int[width + height][width + height];
    	
    	int result = 0;
    	for(int[] coords : diagonals) {
    		// 대각선을 하나 골라서, 양 끝점을 기준으로 나눠 따져본다.
    		int x1 = coords[0]-1;
    		int y1 = coords[1];
    		int x2 = coords[0];
    		int y2 = coords[1]-1;

    		int part1, part2;
    		part1 = comb(x1+y1, x1);
    		part2 = comb(width-x2 + height-y2, width-x2);
    		for(int i = 0; i < part1; i++) {
    			result += part2;
    			result %= 10000019;
    		}
    		part1 = comb(x2+y2, x2);
    		part2 = comb(width-x1 + height-y1, width-x1);
    		for(int i = 0; i < part1; i++) {
    			result += part2;
    			result %= 10000019;
    		}
    	}
        int answer = result;
        return answer;
    }
    private int comb(int n, int r) {
    	if(cache[n][r] != 0) return cache[n][r];
    	if(n == r) return 1;
    	if(r == 0) return 1;
    	cache[n][r] = (comb(n-1, r) + comb(n-1, r-1)) % 10000019;
    	return cache[n][r];
    }
}