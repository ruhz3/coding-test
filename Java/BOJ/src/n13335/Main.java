package n13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Bridge {
	Queue<Integer> bridge;
	int sum = 0;
	int lengthLimit;
	int weightLimit;
	
	public Bridge(int lengthLimit, int weightLimit) {
		this.lengthLimit = lengthLimit;
		this.weightLimit = weightLimit;
		// 다리의 빈 자리에는 0을 채워준다.
		bridge = new LinkedList<Integer>();
		for(int i = 0; i < lengthLimit; i++) {
			bridge.add(0);
		}
	}
	
	/* 무게제한을 통과하면 트럭을, 통과못하면 빈자리를 추가한다.*/
	public boolean addTruck(int truckWeight) {
		// 무게 제한이 넘는다면, 차가 들어오지 못하므로 빈 자리 0을 채워준다.
		if(weightLimit < truckWeight + sum) {
			bridge.add(0);
			return false;
		}
		// 무게 제한이 넘지 않는다면, 트럭이 다리위에 올라온다.
		else {
			sum += truckWeight;
			bridge.add(truckWeight);
			return true;
		}
	}
	
	/* 시간이 지남에 따라 다리 앞자리를 뺴준다.*/
	public void timePass() {
		// 다리를 한 칸 씩 땡겨준다.
		sum -= bridge.poll();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 00. 다리를 생성한다.
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());  // 다리를 건너는 트럭 수
		int w = Integer.parseInt(st.nextToken());  // 다리의 길이 
		int L = Integer.parseInt(st.nextToken());  // 다리의 최대 하중
		Bridge bridge = new Bridge(w, L);
		
		// 01. 트럭 대기열을 생성한다.
		Queue<Integer> trucks = new LinkedList<Integer>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			trucks.add(Integer.parseInt(st.nextToken()));
		}
		
		// 02. 시간이 흐른다.
		int timeCost = 0;
		while(true) {
			// 02-00. 다리를 한 칸 씩 땡겨준다.
			bridge.timePass();
			timeCost++;
			// 02-01. 무게 제한 때문에 들어갈 수 없다면, 들어갈 수 없다면 기다려야 한다.
			if(!trucks.isEmpty() && bridge.addTruck(trucks.peek())) {
				trucks.poll();
			}
			// 02-02. 다리에도, 트럭 대기열에도 아무것도 없다면 다 건넌 것이 된다. 
			if((bridge.sum == 0) && (trucks.size() == 0)) {
				break;
			}
		}
		// 03. 출력한다.
		System.out.println(timeCost);
	}
}
