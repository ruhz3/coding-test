package kakao_2020i_키패드_누르기;

public class Main {
	static int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
	static String hand = "right";
	public static void main(String[] args) {
		String res = new Solution().solution(numbers, hand);
		System.out.println(res);
	}
			
}

class Hand {
	int r, c;
	public Hand(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

class Solution {
	int[][] coords = {
			{3, 1},
			{0, 0}, {0, 1}, {0, 2}, 
			{1, 0}, {1, 1}, {1, 2},
			{2, 0}, {2, 1}, {2, 2}};
	Hand left = new Hand(3, 0);
	Hand right = new Hand(3, 2);
	StringBuilder result = new StringBuilder();
	
	void pressLeft(int r, int c) {
		left.r = r;
		left.c = c;
		result.append("L");
	}
	void pressRight(int r, int c) {
		right.r = r;
		right.c = c;
		result.append("R");
	}
	
	
    public String solution(int[] numbers, String hand) {
        for(int num : numbers) {
        	int r = coords[num][0];
        	int c = coords[num][1];
        	
        	if(c == 0) pressLeft(r, c);
        	else if(c == 2) pressRight(r, c);
        	else {
        		int leftDist = Math.abs(r - left.r) + Math.abs(c - left.c);
        		int rightDist = Math.abs(r - right.r) + Math.abs(c - right.c);        		
        		if((leftDist < rightDist) || (leftDist == rightDist && hand.equals("left"))) pressLeft(r, c);
        		else pressRight(r, c);        		
        	}
        }
        return result.toString();
    }
    
}