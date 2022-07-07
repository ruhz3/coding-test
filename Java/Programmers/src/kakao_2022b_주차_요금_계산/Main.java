package kakao_2022b_주차_요금_계산;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int[] fees = {180, 5000, 10, 600};
	static String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
	
	public static void main(String[] args) {
		int[] res = new Solution().solution(fees, records);
		for(int elem : res) System.out.print(elem + " ");	
	}
}

class Log implements Comparable<Log>{
	String carNum;
	String recentEnterTime;
	int useTime;
	boolean isIn;
	
	public Log(String carNum) {
		this.carNum = carNum;
	}
	@Override
	public int compareTo(Log o) {
		return carNum.compareTo(o.carNum);
	}
}
class Solution {
    public int[] solution(int[] fees, String[] records) {      
    	Map<String, Log> table = new HashMap<>();
        for(String record : records) {
        // 00. 레코드를 파싱한다.
        	StringTokenizer st = new StringTokenizer(record, " ");
        	String time = st.nextToken();
        	String carNumber = st.nextToken();
        	String type = st.nextToken();
        	
        // 01. IN/OUT에 맞게 table의 Log 클래스를 갱신한다.
        	if(type.equals("IN")) {
        		Log log = table.getOrDefault(carNumber, new Log(carNumber));
        		log.recentEnterTime = time;
        		log.isIn = true;
        		table.put(carNumber, log);
        	} else {
        		Log log = table.get(carNumber);
        		log.useTime += getMinuteBetween(log.recentEnterTime, time);
        		log.isIn = false;
        		table.put(carNumber, log);
        	}
        }
        
        // 02. table의 Log 클래스들을 리스트로 가져와 정렬한다.
        List<Log> list = new ArrayList<>(table.values());
        Collections.sort(list);
        
        // 03. 안 나간 차들을 마저 정산하고, 이용 시간을 가져와 요금을 계산한다.  
        int len = list.size();
        int[] answer = new int[len];
        for(int i = 0; i < len; i++) {
        	Log log = list.get(i);
        	if(log.isIn) log.useTime += getMinuteBetween(log.recentEnterTime, "23:59");
        	answer[i] += getFee(fees, log.useTime);
        }
        return answer;
    }
    /* 두 시간 문자열의 차이를 분 단위로 반환*/
    private int getMinuteBetween(String t1, String t2) {
    	int[] start = parseTimeString(t1);
    	int[] end = parseTimeString(t2);
    	if(end[1] < start[1]) {
    		end[1] += 60;
    		end[0]--;
    	}
    	return 60 * (end[0] - start[0]) + (end[1] - start[1]);  
    }
    /* 시간 문자열을 시, 분 배열로 반환*/
    private int[] parseTimeString(String time) {
    	StringTokenizer st = new StringTokenizer(time, ":");
    	int hour = Integer.parseInt(st.nextToken());
    	int minute = Integer.parseInt(st.nextToken());
    	return new int[] {hour, minute};
    }
    /* 분 단위 이용 시간을 요금으로 반환*/
    private int getFee(int[] fees, int time) {
    	// 0, 1, 2, 3 : 기본시간, 기본요금, 단위시간, 단위요금
    	if(time < fees[0]) return fees[1];
    	int unitNum = (time + (fees[2] - 1) - fees[0]) / fees[2];
    	return fees[1] + unitNum * fees[3];
    }
}
