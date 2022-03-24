package TossBank_03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Main {
	static String[] ledgers1 = {"01/01 4 50000","01/11 6 3555","02/01 0 -23555","02/25 5 5000","03/25 0 -15000","06/09 8 43951","12/30 9 99999"};
	static String[] ledgers2 = {"04/01 1 40000","05/01 5 20000","08/31 4 10000","11/11 0 -45000"};
	 
	public static void main(String[] args) throws ParseException {
		int result = new Solution().solution(ledgers2);
		System.out.println(result);
	}
}

class Tx {
	boolean isDeposit;
	Date date;
	int rate;
	int money;
	public Tx(boolean isDeposit, Date date, int rate, int money) {
		this.isDeposit = isDeposit;
		this.date = date;
		this.rate = rate;
		this.money = money;
	}
}

class Solution {
    public int solution(String[] ledgers) throws ParseException {
        int N = ledgers.length;
        
        // 00. 문자열을 Tx 리스트로 생성한다.
        ArrayList<Tx> list = new ArrayList<Tx>();
        StringTokenizer st;
        for(String tx : ledgers) {
        	st = new StringTokenizer(tx, " ");
        	String d = "2022/" + st.nextToken();
        	Date date = new SimpleDateFormat("yyyy/MM/dd").parse(d);
        	int rate = Integer.parseInt(st.nextToken());
        	int money = Integer.parseInt(st.nextToken());
        	list.add(new Tx(money >= 0, date, rate, Math.abs(money)));
        }
        int interestSum = 0;
        
        // 01. 거래내역을 빠른 날짜 부터 출금내역을 찾는다. 
        for(int i = 0; i < N; i++) {
        	Tx tx1 = list.get(i);
        	if(!tx1.isDeposit) {
        		// 02. 찾았다면 이전 내역들 중에서 입금내역을 찾는다.
        		int withdrawMoney = tx1.money;
        		Date withdrawDate = tx1.date;
        		
        		for(int j = i-1; j >= 0; j--) {
        			Tx tx2 = list.get(j);
        			if(tx2.isDeposit) {
        				int depositMoney = tx2.money;
        				Date depositDate = tx2.date;
        				int interestRate = tx2.rate;
        				// 03. 입금된 돈이, 출금할 금액보다 적다면 모두 출금한다. 
        				if(depositMoney <= withdrawMoney) {
        					interestSum += getInterest(depositMoney, interestRate , dateDiff(withdrawDate, depositDate));
        					withdrawMoney -= depositMoney;
        					tx2.money = 0;
        				}
        				// 04. 입급된 돈이, 출금할 금액보다 많다면, 출금할 금액만큼 출금한다.
        				else {
        					interestSum += getInterest(withdrawMoney, interestRate , dateDiff(withdrawDate, depositDate));
        					tx2.money -= withdrawMoney;
        					withdrawMoney = 0;
        					break;
        				}
        			}
        		}
        	}
        }
        // 05. 남은 돈을 모두 출금한다.
        for(int i = 0; i < N; i++) {
        	Tx tx = list.get(i);
        	if(tx.isDeposit && tx.money != 0) {
        		interestSum += getInterest(tx.money, tx.rate, dateDiff(new SimpleDateFormat("yyyy/MM/dd").parse("2022/12/31"), tx.date));
        		tx.money = 0;
        	}
        }
        return interestSum;
    }
    private int getInterest(int money, int rate, int dates) {
    	double ret = (money * rate / 100.0) * (dates / 365.0);
    	return (int)ret;
    }
    
    private int dateDiff(Date date1, Date date2) {
    	long diffTime = date1.getTime() - date2.getTime();
    	int ret = (int)(diffTime / (24 * 60 * 60 * 1000));
    	return ret;
    }
}