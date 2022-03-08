package n20055_컨베이어_벨트_위의_로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Belt {
	int index;
	int durability;
	boolean robotExist;
	
	public Belt(int index, int durability) {
		this.index = index;
		this.durability = durability;
	}
	
	public boolean addRobot() {
		if(!robotExist && durability > 0) {
			robotExist = true;
			durability--;
			return true;
		} else {
			return false;
		}   
	}
	
	public void offRobot() {
		robotExist = false;
	}
}

public class Main {
	static int N, K;
	static LinkedList<Belt> list = new LinkedList<Belt>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 2*N; i++) {
			list.add(new Belt(i, Integer.parseInt(st.nextToken())));
		}
		
		int time = 0;
		while(true) {
			time++;
			
			// 00. 벨트가 각 칸 위에 있는 로봇과 함꼐 한 칸 회전한다.
			list.addFirst(list.pollLast());
			list.get(N-1).offRobot();
			
			// 01. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
			for(int i = N-1; i >= 1; i--) {
				if(list.get(i-1).robotExist)
					if(list.get(i).addRobot())
						list.get(i-1).offRobot();
			}
			list.get(N-1).offRobot();
			
			// 02. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			list.get(0).addRobot();  
			
			// 03. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다.
			int count = 0;
			for(int i = 0; i < 2*N; i++) {
				if(list.get(i).durability <= 0) count++;
			}
			if(count >= K) break;	
		}
		System.out.println(time);
	}
}
