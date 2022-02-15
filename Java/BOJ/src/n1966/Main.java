package n1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static int print(LinkedList<Integer> queue, int[] priority) {
		int first;
		boolean canPrint;
		while(true) {
			// 00. canPrint가 중간에 false로 바뀌지 않는다면 출력될 것이다. 
			canPrint = true;
			
			// 01. 대기열의 첫 번째 원소 뒤에 우선순위가 높은 것이 있다면, 맨 뒤로 보내고 canPrint를 false로 변경한다.  
			first = queue.peek();
			for(int i = 1, len = queue.size(); i < len; i++) {
				if(priority[queue.get(i)] > priority[first]) {
					queue.add(queue.poll());
					canPrint = false;
					break;
				}
			}
			
			// 02. 뒤에 우선순위가 더 큰 것이 없어지면, 이 조건문을 통과할 것이다. 
			if(canPrint)
				break;
		}
		return queue.poll();
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int N, M;
		LinkedList<Integer> queue = new LinkedList<>();
		int[] priority;
		int count;
		while(T > 0) {
			// 00. N, M을 입력 받는다.
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 01. 현재 큐 상태를 입력 받는다.
			queue.clear();
			priority = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				priority[i] = Integer.parseInt(st.nextToken()); 
				queue.add(i);
			}
			
			// 02. 출력을 진행하고, 원하는 문서 번호가 나오면 몇 번만에 나왔는지 출력한다.
			count = 1;
			while(true) {
				if(print(queue, priority) == M) {
					System.out.println(count);
					break;
				} else {
					count++;
				}
			}
			T--;
		}
	}
}

