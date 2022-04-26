package dm_be_2021_행렬_테두리_회전하기;

public class Main {
	public static void main(String[] args) {
		int rows = 6;
		int columns = 6;
		int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
		
		int[] res = new Solution().solution(rows, columns, queries);
		for(int elem : res) {
			System.out.print(elem + " ");
		}
	}
}

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int N = queries.length;
        int[] answer = new int[N];
        
        // 00. 숫자가 순서대로 입력된 배열 map을 생성한다.
        int[][] map = new int[rows+1][columns+1];
        for(int i = 1, idx = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) {
                map[i][j] = idx++;
            }
        }
        // 01. 각 회전을 수행한다.
        for(int i = 0; i < N; i++) {
        	// 01-00. 회전 정보를 가져온다.
            int[] query = queries[i];
            int sr = query[0], sc = query[1];
            int er = query[2], ec = query[3];
            int R = er - sr + 1;
            int C = ec - sc + 1;
            
            // 01-01. 회전 시키되, 첫번째 가려지는 원소는 개별지정한다.
            int tmp = map[sr][sc];
            int minNum = map[sr][sc];
            for(int k = 0; k < R - 1; k++) {
                map[sr + k][sc] = map[sr + k + 1][sc];
                minNum = Math.min(minNum, map[sr + k + 1][sc]);
            }
            for(int k = 0; k < C - 1; k++) {
                map[er][sc + k] = map[er][sc + k + 1];
                minNum = Math.min(minNum, map[er][sc + k + 1]);
            }
            for(int k = 0; k < R - 1; k++) {
                map[er - k][ec] = map[er - k - 1][ec];
                minNum = Math.min(minNum, map[er - k - 1][ec]);
            }
            for(int k = 0; k < C - 1 ; k++) {
                map[sr][ec - k] = map[sr][ec - k - 1];
                minNum = Math.min(minNum, map[sr][ec - k - 1]);
            }
            map[sr][sc + 1] = tmp;
            
            // 01-02.회전 결과, 가장 작았던 번호를 저장한다.
            answer[i] = minNum;
        }
        
        return answer;
    }
}