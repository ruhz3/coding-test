package n2239_스도쿠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static ArrayList<int[]> zeros = new ArrayList<>();
	static boolean isFinished;
    static int[][] map;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        for (int i = 1; i <= 9; i++) {
            String input = br.readLine();
            for (int j = 1; j <= 9; j++) {
                map[i][j] = input.charAt(j-1) - '0';
                if (map[i][j] == 0) zeros.add(new int[]{i, j});
            }
        }
        dfs(0);
    }

    public static void dfs(int idx) {
        if (idx == zeros.size()) {
            isFinished = true;
            print();
            return;
        }
        if (isFinished) return;
        int[] cur = zeros.get(idx);
        for (int j = 1; j <= 9; j++) {
            if (map[cur[0]][cur[1]] == 0 && check(cur[0], cur[1], j)) {
                map[cur[0]][cur[1]] = j;
                dfs(idx + 1);
                map[cur[0]][cur[1]] = 0;
            }
        }
    }


    public static boolean check(int x, int y, int val) {
        for (int i = 1; i <= 9; i++) {
            if (y != i && map[x][i] == val) return false;
            if (x != i && map[i][y] == val) return false;
        }
        int xRange, yRange;
        if (x % 3 == 0) xRange = x - 2;
        else xRange = x - x % 3 + 1;
        if (y % 3 == 0) yRange = y - 2;
        else yRange = y - y % 3 + 1;

        for (int i = xRange; i < xRange + 3; i++)
            for (int j = yRange; j < yRange + 3; j++)
                if (x != i && y != j && map[i][j] == val) return false;

        return true;
    }
    
    private static void print() {
    	StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++)
                sb.append(map[i][j]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}