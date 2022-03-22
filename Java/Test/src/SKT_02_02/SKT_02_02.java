package SKT_02_02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SKT_02_02 {
	static String[] arr1 = {"1","2","4","3","3","4","1","5"};
	static String[] process1 = {"read 1 3 1 2","read 2 6 4 7","write 4 3 3 5 2","read 5 2 2 5","write 6 1 3 3 9", "read 9 1 0 7"};
	static String[] arr2 = {"1","1","1","1","1","1","1"};
	static String[] process2 = {"write 1 12 1 5 8","read 2 3 0 2","read 5 5 1 2","read 7 5 2 5","write 13 4 0 1 3","write 19 3 3 5 5","read 30 4 0 6","read 32 3 1 5"};
	
	public static void main(String[] args) {
		String[] res = new Solution().solution(arr2, process2);
		for(int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
	}
}
class Command {
	int num;
	String type;
	int time;
	int cost;
	int beginIndex;
	int endIndex;
	String value;
	String[] arr;
	
	public Command(String command, String[] arr, int num) {
		// 커맨드 해석
		StringTokenizer st = new StringTokenizer(command);
		type = st.nextToken();
		time = Integer.parseInt(st.nextToken());
		cost = Integer.parseInt(st.nextToken());
		beginIndex = Integer.parseInt(st.nextToken());
		endIndex = Integer.parseInt(st.nextToken());
		if(type.equals("write")) value = st.nextToken();
		// 맵과 연결
		this.arr = arr;
		// 번호 부여
		this.num = num;		
	}
	
	public boolean run() {
		if(--cost <= 0) return true;
		return false;
	}
	
	public String print() {
		StringBuilder sb = new StringBuilder();
		for(int i = beginIndex; i <= endIndex; i++) {
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	public void write() {
		for(int i = beginIndex; i <= endIndex; i++) {
			arr[i] = value;
		}
	}
}

class Solution {
    public String[] solution(String[] arr, String[] processes) {
        Queue<Command> list = new LinkedList<Command>();
        Queue<Command> readQueue = new LinkedList<Command>();
        Queue<Command> writeQueue = new LinkedList<Command>();
        LinkedList<Command> now = new LinkedList<Command>();
        int N;
        char status;
        
        N = arr.length;
        status = 'N';
        int readCount = 0;
        for(int i = 0; i < processes.length; i++) {
        	String command = processes[i];
        	Command c = new Command(command, arr, i);
        	if(c.type.equals("read")) readCount++;
        	list.offer(c);
        }
        String[] result = new String[processes.length];
        String[] answer = new String[readCount+1];
        
        int time = 0;
        int emptyCount = 0;
        while(true) {
        	// 00. 시간이 되면 프로세스를 list에서 알맞은 Queue로 넣어준다.
        	while(true) {
            	if(list.isEmpty() || list.peek().time > time) break;
            	Command c = list.poll();
            	if(c.type.equals("read")) readQueue.offer(c);
            	else writeQueue.offer(c);
            }
        	
        	// 01. 쓰기 작업을 먼저 확인해준다. (아무것도 안 하고 있을 때만 쓰기를 시작할 수 있다)
        	if(status == 'N' && !writeQueue.isEmpty()) {
        		now.add(writeQueue.poll());
        		status = 'W';
        	}
        	
        	// 02. 읽기 작업을 다음으로 확인해준다.(쓰는 중이 아니면서 쓰기 명령이 대기 중이지 않을 때 쓸 수 있다)
        	while(status != 'W' && writeQueue.isEmpty() && !readQueue.isEmpty()) {
        		now.add(readQueue.poll());
        		status = 'R';
        	}
        	if(now.isEmpty()) emptyCount++;
        	
        	
        	// 03. 시간을 진행시켜준다.
        	int nowLen = now.size();
        	for(int i = nowLen-1; i >= 0; i--) {
        		Command elem = now.get(i);
        		if(elem.run()) {
        			if(elem.type.equals("read")) {
        				result[elem.num] = elem.print();
        			}
        			else elem.write();
        			now.remove(i);
        		}
        	}
        	if(now.isEmpty()) status = 'N';
        	
        	if(list.isEmpty() && readQueue.isEmpty() && writeQueue.isEmpty() && now.isEmpty()) break;
        	time++;
        }
        int idx = 0;
        for(int i = 0; i < processes.length; i++) {
        	if(result[i] == null) {
        		continue;
        	}
        	answer[idx++] = result[i];
        }
        answer[readCount] = Integer.toString(time-emptyCount + 1);
        return answer;
    }
}