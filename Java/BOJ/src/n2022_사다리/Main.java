package n2022_사다리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static double x, y, c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		x = Double.parseDouble(st.nextToken());
		y = Double.parseDouble(st.nextToken());
		c = Double.parseDouble(st.nextToken());
		
		double result = 0;
		double left = 0;
		double right = Math.min(x, y);
		while(true) {
			double mid = (left+right)/2;
			double diff = calculate(mid);
			if(Double.compare(Math.abs(diff),0.000001) < 0) {
				result = mid;
				break;
			}
			 // 결과가 음수가 나온다면, 우변이 작아져야 하므로 작게 탐색해야 한다.
			if(diff < 0) right = mid;
			 // 결과가 양수가 나온다면, 우변이 커져야 하므로 크게 탐색해야 한다.
			else left = mid;
		}
		System.out.printf("%.3f", result);
	}
	
	private static double calculate(double k) {
		// 좌변 : 상수, 우변 : k를 포함한 식(k와 비례)
		double L = (double)1/c;
		double R = (double)1 /Math.sqrt(Math.pow(x, 2) - Math.pow(k, 2)) + (double)1 /Math.sqrt(Math.pow(y, 2) - Math.pow(k, 2));
		return L - R;
	}
}
