package TossBank_04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class Main {
	static final String s = "2021:04:12:16:08:35";
	static final String[] times = {"01:06:30:00", "01:04:12:00"};
	public static void main(String[] args) throws ParseException {
		int[] result = new Solution().solution(s, times);
		for(int elem : result) System.out.println(elem);
	}
}

class Solution {
	static Date nextDate;
	
    public int[] solution(String s, String[] times) throws ParseException {
        int[] answer = new int[2];
        // 00. 현재 날짜를 캘린더에 설정한다. 
        Calendar cal = Calendar.getInstance();
        Date prevDate = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").parse(s);
        cal.setTime(prevDate);
        
        StringTokenizer st;
        boolean isDone = true;
        for(String t : times) {
        	// 01. 1일1저축을 지키기 위한 기한을 결정한다.
        	if(isDone) getNextDate(cal);
        
        	// 02. 주어진 시간 만큼 더한다.
        	st = new StringTokenizer(t, ":");
        	cal.add(Calendar.DATE, Integer.parseInt(st.nextToken()));
        	cal.add(Calendar.HOUR, Integer.parseInt(st.nextToken()));
        	cal.add(Calendar.MINUTE, Integer.parseInt(st.nextToken()));
        	cal.add(Calendar.SECOND, Integer.parseInt(st.nextToken()));
        	
        	// 03. 더한 시간이 기한을 넘겼다면, 실패다. 
        	if(cal.getTime().after(nextDate)) isDone = false;
        }
        
        // 04. 결과를 출력한다.
        if(isDone) answer[0] = 1;
        
        Date startDate = new SimpleDateFormat("yyyy:MM:dd").parse(s);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date endDate = cal.getTime();
        answer[1] = dateDiff(endDate, startDate);
        return answer;
    }
    
    private void getNextDate(Calendar cal) {
    	Date date = cal.getTime();
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        nextDate = cal.getTime();
        cal.setTime(date);
    }
    
    private int dateDiff(Date date1, Date date2) {
    	long diffTime = date1.getTime() - date2.getTime();
    	int ret = (int)(diffTime / (24 * 60 * 60 * 1000));
    	return ret + 1;
    }
}
