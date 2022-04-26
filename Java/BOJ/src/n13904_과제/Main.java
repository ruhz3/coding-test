package n13904_과제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Homework {
	int date;
	int value;
	public Homework(int date, int value) {
		super();
		this.date = date;
		this.value = value;
	}
}

public class Main {
	static PriorityQueue<Homework> homeworks = new PriorityQueue<>(
			(o1, o2) -> {return o2.date - o1.date;});
	static PriorityQueue<Homework> pQueue = new PriorityQueue<>(
			(o1, o2) -> {return o2.value - o1.value;});
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		// 00. 입력하며, 과제가 모두 끝나는 날을 구한다.
		int lastDate = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int date = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			homeworks.offer(new Homework(date, value));
			lastDate = Math.max(lastDate, date);
		}
		// 01. 마지막 날 부터 할 수 있는 과제 중, 가장 점수가 높은 것을 처리한다.
		int day = lastDate;
		int score = 0;
		while(day > 0) {
			while(!homeworks.isEmpty() && homeworks.peek().date >= day) {
				Homework hw = homeworks.poll();
				pQueue.offer(hw);
			}
			if(!pQueue.isEmpty()) {
				Homework hw = pQueue.poll();
				score += hw.value;
			}
			day--;
		}
		System.out.println(score);
	}
}
