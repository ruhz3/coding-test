package sw_2019_멀쩡한_사각형;

public class Main {
	public static void main(String[] args) {
		long res = new Solution().solution(8, 12);
		System.out.println(res);
	}
}
class Solution {
    public long solution(int w, int h) {
    	if(w == h) {
    		return (long)w * w - w;
    	}
    	// 00. 긴 변과 작은 변으로 구분한다.
        int l, s;
        if(w > h) {
        	l = w; s = h; 
        } else {
        	l = h; s = w;
        }
        
        // 01. 최대 공약수로, 반복되는 모양을 찾아 개수를 구한다.
        int gcd = getGCD(l, s);
        l /= gcd;
        s /= gcd;
        long unit = getSquareCount(l, s);
        
        // 02. 전체 개수에서 반복 모양 * 반복 횟수를 빼서 반환한다. 
        return (long)w * h - (long)unit * gcd;
    }
    /* 유클리드 호제법*/
    private int getGCD(int a, int b) {
    	int r = 0;
    	while(b != 0) {
    		r = a % b;
    		a = b;
    		b = r;
    	}
    	return a;
    }
    /* 해당 구간에서의 개수 반환*/
    private long getSquareCount(int l, int s) {
    	long count = 0;
    	
    	long prev = 0;
    	for(int i = 1; i <= s; i++) {
    		long now = (long)l * i / s;
    		long r = (long)l * i % s;
    		
    		if(r != 0) now++;
    		count += now - prev;
    		if(r != 0) now--;
    		prev = now;
    	}
    	return count;
    }
}