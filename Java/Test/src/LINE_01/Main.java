package LINE_01;


public class Main {
	static String[] logs = {
			"team_name : db application_name : dbtest error_level : info message : test",
			"team_name : test application_name : I DONT CARE error_level : error message : x",
			"team_name : ThisIsJustForTest application_name : TestAndTestAndTestAndTest error_level : test message : IAlwaysTestingAndIWillTestForever",
			"team_name : oberervability application_name : LogViewer error_level : error"
			};
	
	public static void main(String[] args) {
		int res = new Solution().solution(logs);
		System.out.println(res);
	}
}

class Solution {
	// "team_name : t application_name : a error_level : e message : m"
	static final String[] columns = {
			"team_name : ",
			"application_name : ",
			"error_level : ",
			"message : ",
			};
	
    public int solution(String[] logs) {
        int answer = 0;
        for(String log : logs) {
        	int len = log.length();
        	// 00. 길이 조건을  검사한다.
        	if(len > 100) {
        		answer++;
        		continue;
        	}
        	// 01. 항목별로 검사한다.
        	int logIdx = 0;
        	int columnIdx = 0;
        	boolean isWrong = false;
        	for(int i = 0; i < 4; i++) {
        		// 02. 미리 정의해둔 항목명과 일치하는지 검사한다. 
        		int colLen = columns[columnIdx].length();
        		if(logIdx + colLen >= len) {
        			isWrong = true;
        			break;
        		}
				if(logIdx + colLen < len && log.substring(logIdx, logIdx+colLen).equals(columns[columnIdx])) {
					logIdx += colLen;
					columnIdx++;
				} else {
					isWrong = true;
					break;
				}
				// 03. 메세지 부분의 시작과 끝을 공백으로 하고, 사이에 문자가 모두 알파벳인지 검사한다.
				while(logIdx < len) {
					char elem = log.charAt(logIdx);
					if(elem == ' ') break;
					if(!((elem >= 'A' && elem <='Z') || (elem >= 'a' && elem <= 'z'))) {
						isWrong = true;
						break;
					}
					logIdx++;
				}
				if(isWrong) break;
				// 04. 다음 항목을 위해 인덱스를 증가시킨다.
				logIdx++;
        	}
        	// 05. 최종적으로 해당 log가 이상이 있었는지 확인한다.
        	if(isWrong) answer++;
        }
        return answer;
    }
}