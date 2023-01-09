package KAKAO_01;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		String today = "2022.05.19";
		String[] terms = {"A 6", "B 12", "C 3"};
		String[] privacies = {
				"2021.05.02 A",
				"2021.07.01 B",
				"2022.02.19 C",
				"2022.02.20 C"};
		int[] res = new Solution().solution(today, terms, privacies);
		for(int elem : res) {
			System.out.println(elem);
		}
	}
}
class Solution {
	int[] table;
	int[] now;
	int N;
	
    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer st;
        // 00. 날짜를 파싱한다.
        now = parseDate(today);
        
        // 01. 약관을 파싱한다.
        table = new int['Z' - 'A'];
        for(String term : terms) {
        	st = new StringTokenizer(term, " ");
        	int key = st.nextToken().charAt(0) - 'A';
        	int value = Integer.parseInt(st.nextToken());
        	table[key] = value;
        }
        
        // 01. 개인 정보들을 하나씩 보며 걸러낸다.
        N = privacies.length;
        ArrayList<Integer> list = new ArrayList<>();
        for(int n = 0; n < N; n++) {
        	st = new StringTokenizer(privacies[n], " ");
        	int[] date = parseDate(st.nextToken());
        	int type = st.nextToken().charAt(0) - 'A';
        	int[] expireDate = dateAfterMonths(date, table[type]);
        	if(isExpired(expireDate)) {
        		list.add(n+1);
        	}
        }
        int len = list.size();
        int[] answer = new int[len];
        for(int i = 0; i < len;  i++) {
        	answer[i] = list.get(i);
        }
        return answer;
    }
    private int[] parseDate(String date) {
    	int[] ret = new int[3];
    	StringTokenizer st = new StringTokenizer(date, ".");
        ret[0] = Integer.parseInt(st.nextToken());
        ret[1] = Integer.parseInt(st.nextToken());;
        ret[2] = Integer.parseInt(st.nextToken());;
        return ret;
    }
    private int[] dateAfterMonths(int[] date, int month) {
    	int[] ret = new int[3];
    	ret[0] = date[0] + month / 12;
    	ret[1] = date[1] + month % 12;
    	if(ret[1] > 12) {
    		ret[0] += 1;
    		ret[1] -= 12;
    	}
    	ret[2] = date[2];
    	return ret;
    }
    private boolean isExpired(int[] expire) {
    	for(int i = 0; i < 3; i++) {
    		if(now[i] > expire[i]) return true;
        	if(now[i] < expire[i]) return false;
    	}
    	return true;
    }
}