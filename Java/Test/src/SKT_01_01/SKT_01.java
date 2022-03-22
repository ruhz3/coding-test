package SKT_01_01;
import java.util.ArrayList;
import java.util.Collections;

public class SKT_01 {
	public static void main(String[] args) {
		System.out.println(new Solution().solution(4578, new int[] {1, 4, 99, 35, 50, 1000}));
	}
}

class Coin implements Comparable<Coin>{
	int value;
	int cost;
	double rate;
	public Coin(int value, int cost, double rate) {
		this.value = value;
		this.cost = cost;
		this.rate = rate;
	}
	@Override
	public int compareTo(Coin o) {
		if(o.rate < rate) return 1;
		else if(o.rate == rate) return 0;
		else return -1;
	}
	
}

class Solution {
    public int solution(int money, int[] costs) {
    	final int N = 6;
        final int[] values = {1, 5, 10, 50, 100, 500};
    	
    	ArrayList<Coin> list = new ArrayList<Coin>();
        for(int i = 0; i < N; i++) {
        	list.add(new Coin(values[i], costs[i], (double)costs[i] / values[i]));
        }
        Collections.sort(list);
        
        int price = 0;
        for(int i = 0; i < N; i++) {
        	int val = list.get(i).value;
        	price += (money / val) * list.get(i).cost;
        	money %= val;
        }
        
    	int answer = price;
        return answer;
    }
}
