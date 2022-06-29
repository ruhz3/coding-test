package weekly_부족한_금액_계산하기;

class Solution {
    public long solution(int price, int money, int count) {
        long p = price;
        long m = money;
        long c = count;
        long answer = c * (c + 1) / 2 * p;
        return answer > m ? answer - m : 0;
    }
}