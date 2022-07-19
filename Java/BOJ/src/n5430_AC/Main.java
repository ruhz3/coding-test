package n5430_AC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			// 00. 입력한다.
			String p = br.readLine();
			int len = Integer.parseInt(br.readLine());
			String arrStr = br.readLine();

			// 01. 배열정보를 파싱해 배열에 저장한다.
			int[] arr = new int[len];
			StringTokenizer st = new StringTokenizer(arrStr.substring(1, arrStr.length() - 1), ",");
			for (int i = 0; i < len; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 02. 함수를 실행한다.
			int start = 0;
			int end = len - 1;
			boolean isReversed = false;
			boolean isError = false;
			for (int i = 0, l = p.length(); i < l; i++) {
				// 02-00. 'R'일 때, flag를 바꿔준다.
				if (p.charAt(i) == 'R') {
					isReversed = !isReversed;
				// 02-01. 'D'일때, flag에 맞춰 인덱스를 줄여준다.
				} else {
					if (!isReversed) start++;
					else end--;
					if (start > end + 1) {
						isError = true;
						break;
					}
				}
			}
			// 03. 함수 결과에 맞게 출력한다.
			if (isError)
				sb.append("error\n");
			else {
				sb.append('[');
				if (isReversed) {
					for (int i = end; i >= start; i--)
						sb.append(arr[i]).append(',');
				} else {
					for (int i = start; i <= end; i++)
						sb.append(arr[i]).append(',');
				}
				sb.deleteCharAt(sb.lastIndexOf(",")).append("]\n");
			}
		}
		System.out.println(sb.toString());
	}
}
