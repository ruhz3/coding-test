package kakao_2022b_신고_결과_받기;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static String[] id_list = {"muzi", "frodo", "apeach", "neo"};
	static String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
	static int k = 2;
	public static void main(String[] args) {
		int[] res = new Solution().solution(id_list, report, k);
		for(int elem : res) {
			System.out.print(elem + ", ");
		}
	}
}

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int N = id_list.length;
        Map<String, Integer> dict = new HashMap<>();
        boolean[][] isReported = new boolean[N][N];
        int[] answer = new int[N];
        int[] sum = new int[N];
        
        // 00. 아이디를 숫자로 치환할 수 있도록 Map을 작성한다.
        for(int i = 0; i < N; i++)
        	dict.put(id_list[i], i);
        
        // 01. report를 파싱해서, 신고 여부와 누적 피신고 횟수를 계산한다.
        for(String text : report) {
        	StringTokenizer st = new StringTokenizer(text, " ");
        	int from = dict.get(st.nextToken());
        	int to = dict.get(st.nextToken());
        	if(!isReported[from][to]) {
        		isReported[from][to] = true;
        		sum[to]++;
        	}        	
        }
        // 02. 누적 피신고 횟수가 기준치를 넘으면, 신고 여부를 보고 처리결과를 발송한다.
        for(int to = 0; to < N; to++) {
        	if(sum[to] < k) continue;
        	for(int from = 0; from < N; from++)
    			if(isReported[from][to]) answer[from]++;
        }
        
        return answer;
    }
}