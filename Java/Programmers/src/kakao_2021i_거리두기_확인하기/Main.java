package kakao_2021i_거리두기_확인하기;

public class Main {
	static String[][] example = {
			{"OOPOO", "OPOOO", "OOOOO", "OOOOO", "OOOOO"},
			{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
			{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
			{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
			{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
	public static void main(String[] args) {
		int[] res = new Solution().solution(example);
		for(int elem : res) {
			System.out.println(elem);			
		}
	}
}

class Solution {
    public int[] solution(String[][] places) {
    	int[] rowDir = {-1, 0, 1, 0};  // 상, 우, 하, 좌
    	int[] colDir = {0, 1, 0, -1};
        int[] answer = {1, 1, 1, 1, 1};
        char[][] room = new char[5][];
        
        for(int roomNum = 0; roomNum < 5; roomNum++) {
        	for(int i = 0; i < 5; i++) {
        		room[i] = places[roomNum][i].toCharArray();
        	}

        OUTER : for(int r = 0; r < 5; r++) {
        		for(int c = 0; c < 5; c++) {
        			// 00. P를 찾으면, 최대 깊이 2로 4방 탐색한다.
        			if(room[r][c] != 'P') continue;
        			for(int firstDir = 0; firstDir < 4; firstDir++) {
        				int fnr = r + rowDir[firstDir];
        				int fnc = c + colDir[firstDir];
        				if(fnr < 0 || fnr >= 5 || fnc < 0 || fnc >= 5) continue;
        				if(room[fnr][fnc] == 'X') continue;
        				
        				// 01. 깊이 1일 때 'P'를 만나면 탈출하고, 
        				if(room[fnr][fnc] == 'P') {
        					answer[roomNum] = 0;
        					break OUTER;
        				}
        				
        				// 02. 깊이 1일 때 'O'를 만나면 깊이 2로 들어간다. 
        				for(int secondDir = 0; secondDir < 4; secondDir++) {
        					int snr = fnr + rowDir[secondDir];
            				int snc = fnc + colDir[secondDir];
            				if(snr < 0 || snr >= 5 || snc < 0 || snc >= 5) continue;
            				if(snr == r && snc == c) continue;
            			
            				// 03. 깊이 2에서 P를 만나면 거리두기 탈출한다. 
            				if(room[snr][snc] == 'P') {
            					answer[roomNum] = 0;
            					break OUTER;
            				}
            			}
        			}
        		}
        	}
        }
        return answer;
    }
}