package n1451_직사각형으로_나누기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int map[][] = new int[101][101];
	
	/* 합 */
	public static long sum(int x1, int y1, int x2, int y2) {
		long result = 0;
		for(int i = y1; i <= y2; i++) {
			for(int j = x1; j <= x2; j++) {
				result += map[i][j];
			}
		}
		return result;
	}
	
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 00. 입력한다.
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
        	String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        
        long max = 0;
    	// 01. 패턴 1
    	for (int i = 0; i < M - 2; i++) {
    		for (int j = i + 1; j < M - 1; j++) {
    			long a = sum(0, 0, i, N - 1);
    			long b = sum(i + 1, 0, j, N - 1);
    			long c = sum(j + 1, 0, M - 1, N - 1);
    			if(max < a*b*c) {
    				max = a*b*c;
    			}
    		}
    	}

    	// 02. 패턴 2
    	for (int i = 0; i < N - 2; i++) {
    		for (int j = i + 1; j < N - 1; j++) {
    			long a = sum(0, 0, M - 1, i);
    			long b = sum(0, i + 1, M - 1, j);
    			long c = sum(0, j + 1, M - 1, N - 1);
    			if(max < a*b*c) {
    				max = a*b*c;
    			}
    		}
    	}

    	// 03. 패턴 3
    	for (int i = 0; i < M - 1; i++) {
    		for (int j = 0; j < N - 1; j++) {
    			long a = sum(0, 0, i, N - 1);
    			long b = sum(i + 1, 0, M - 1, j);
    			long c = sum(i + 1, j + 1, M - 1, N - 1);
    			if(max < a*b*c) {
    				max = a*b*c;
    			}
    		}
    	}

    	// 04. 패턴 4
    	for (int i = M - 1; i > 0; i--) {
    		for (int j = 0; j < N - 1; j++) {
    			long a = sum(i, 0, M - 1, N - 1);
    			long b = sum(0, 0, i - 1, j);
    			long c = sum(0, j + 1, i - 1, N - 1);
    			if(max < a*b*c) {
    				max = a*b*c;
    			}
    		}
    	}

    	// 05. 패턴 5
    	for (int i = 0; i < N - 1; i++) {
    		for (int j = 0; j < M - 1; j++) {
    			long a = sum(0, 0, M - 1, i);
    			long b = sum(0, i + 1, j, N - 1);
    			long c = sum(j + 1, i + 1, M - 1, N - 1);
    			if(max < a*b*c) {
    				max = a*b*c;
    			}
    		}
    	}

    	// 06. 패턴 6
    	for (int i = N - 1; i > 0; i--) {
    		for (int j = 0; j < M - 1; j++) {
    			long a = sum(0, i, M - 1, N - 1);
    			long b = sum(0, 0, j, i - 1);
    			long c = sum(j + 1, 0, M - 1, i - 1);
    			if(max < a*b*c) {
    				max = a*b*c;
    			}
    		}
    	}
    	
    	System.out.println(max);
    } 
}
