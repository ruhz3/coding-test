package kakao_2019b_실패율;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int[] res = new Solution().solution(4, new int[]{4, 4, 4, 4, 4});
		for(int elem : res) {
			System.out.print(elem + " ");
		}
	}
}
class Stage implements Comparable<Stage>{
	int num;
	float ratio;
	public Stage(int num) {
		super();
		this.num = num;
	}
	@Override
	public int compareTo(Stage o) {
		if(this.ratio > o.ratio) return -1;
		else if(this.ratio < o.ratio) return 1;
		else return this.num - o.num;
	}
}
class Solution {
    public int[] solution(int N, int[] stages) {
        Stage[] list = new Stage[N]; 
        int[] answer = new int[N];

        Arrays.sort(stages);
        int userNum = stages.length;
        int idx = 0;
        for(int i = 1; i <= N; i++) {
        	list[i-1] = new Stage(i);
        	int count = 0;
        	while(idx < stages.length && stages[idx] <= i) {
        		count++;
        		idx++;
        	}
        	list[i-1].ratio = (float)count/userNum;
        	userNum -= count;
        }
        
        Arrays.sort(list);
        for(int i = 0; i < N; i++) {
        	answer[i] = list[i].num;
        }
        return answer;
    }
}