package KAKAO_02;

public class Main {
	public static void main(String[] args) {
		int cap = 2;
		int n= 7;
		int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
		int[] pickups = {0, 2, 0, 1, 0, 2, 0};
//		int cap = 4;
//		int n= 5;
//		int[] deliveries = {1, 0, 3, 1, 2};
//		int[] pickups = {0, 3, 0, 4, 0};
		
		long res = new Solution().solution(cap, n, deliveries, pickups);
		System.out.println(res);
	}
}
class Solution {
	int lastIndex;
	
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		// 00. 각 배송/수거의 가장 마지막 집을 찾는다.
		lastIndex = n-1;
		while(deliveries[lastIndex] == 0 && pickups[lastIndex] == 0) {lastIndex--;}
		
		long distance = 0;
		while(lastIndex >= 0) {
			print(deliveries);
			print(pickups);
			System.out.println(lastIndex+1);
			distance += (lastIndex + 1) * 2;
			// 01. 뒷쪽 부터 몇개를 배달할 수 있는지 세어본다.
			int deliverCount = 0;
			int pickupCount = 0;
			boolean isLastIndexSet = false;
			for(int i = lastIndex; i >= 0; i--) {
				if(deliveries[i] == 0 && pickups[i] == 0) continue;
				
				boolean isDeliverable = deliveries[i] <= cap - deliverCount;
				boolean isPickupable = pickups[i] <= cap - pickupCount;
				if(isDeliverable && isPickupable) {
					deliverCount += deliveries[i];
					pickupCount += pickups[i];
					deliveries[i] = 0;
					pickups[i] = 0;
				} else {
					isLastIndexSet = true;
					lastIndex = i;
					break;
				}
			}
			if((deliverCount == 0 && pickupCount == 0) || !isLastIndexSet) lastIndex = -1;
		}
		return distance;
	}
	private void print(int[] arr) {
		for(int elem : arr) {
			System.out.print(elem);
		} System.out.println();
	}
}
