package kakao_2019i_크레인_인형뽑기_게임;

import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		int[][] board = {
				{0,0,0,0,0},
				{0,0,1,0,3},
				{0,2,5,0,1},
				{4,2,4,4,2},
				{3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		new Solution().solution(board, moves);
	}
}

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N = board.length;
        Stack<Integer> stack = new Stack<Integer>();
        for(int move : moves) {
            for(int i = 0; i < N; i++) {
                int doll = board[i][move-1];
                if(doll == 0) continue;
                board[i][move-1] = 0;
                if(!stack.isEmpty() && stack.peek() == doll) {
                	stack.pop();
                    answer += 2;
                } else {
                	stack.add(doll);
                }
                break;
            }
        }
        return answer;
    }
}