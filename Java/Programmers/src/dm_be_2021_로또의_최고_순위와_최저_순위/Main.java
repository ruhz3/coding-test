package dm_be_2021_로또의_최고_순위와_최저_순위;

public class Main {
	public static void main(String[] args) {
		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		int[] res = new Solution().solution(lottos, win_nums);
		for(int elem : res) {
			System.out.print(elem + " ");
		}
	}
}
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
    	int matchCount = 0;
        int zeroCount = 0;
        for(int lotto : lottos) {
        	// 00. 몇개가 지워졌는지 구한다.
            if(lotto == 0) {
                zeroCount++;
                continue;
            }
            // 01. 몇개가 맞았는지 구한다. 
            boolean isMatched = false;
            for(int winNum : win_nums) {
                if(winNum == lotto) {
                    isMatched = true;
                    break;
                }
            }
            if(isMatched) matchCount++;
        }
        // 02. 결과에 맞게 출력한다.
        int maxScore = Math.min(7 - matchCount - zeroCount, 6);
        int minScore = Math.min(7 - matchCount, 6);        
        return new int[]{maxScore, minScore};
    }
}