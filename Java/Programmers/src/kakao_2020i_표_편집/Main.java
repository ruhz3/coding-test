package kakao_2020i_표_편집;

import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		String res = new Solution().solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"});
		System.out.println(res);
	}
}
class Solution {
	Stack<Integer> bin = new Stack<>();
	int pointer;
	int size;
	
    public String solution(int n, int k, String[] cmd) {
    	pointer = k;
    	size = n;
    	// 00. 명령을 실행한다.
        for(String command : cmd) exec(command);
        
        // 01. 삭제된 부분에만 X를 채운다.
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) sb.append("O");
        while(!bin.isEmpty()) sb.insert(bin.pop().intValue(), 'X');
        
        // 02. 출력한다.
        return sb.toString();
    }
    /* 명령을 실행한다.*/
    private void exec(String command) {
    	StringTokenizer st = new StringTokenizer(command, " ");
    	String key = st.nextToken();
    	
    	if(key.equals("C")) delete();
    	else if(key.equals("Z")) restore();
    	else movePointer(key, Integer.parseInt(st.nextToken()));
    }
    /* 삭제 명령*/
    private void delete() {
    	bin.push(pointer);
    	size--;
    	if(pointer == size) pointer--;
    }
    /* 복구 명령*/
    private void restore() {
    	int num = bin.pop();
    	if(pointer >= num) pointer++;
    	size++;
    }
    /* 이동 명령*/
    private void movePointer(String key, int d) {
    	if(key.equals("U")) {
    		pointer -= d;
    	} else {
    		pointer += d;
    	}
    }
}