package n2961_도영이가_만든_맛있는_음식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[][] ingrds;
	static boolean[] recipe;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		ingrds = new long[N][2];
		recipe = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ingrds[i][0] = Long.parseLong(st.nextToken());
			ingrds[i][1] = Long.parseLong(st.nextToken());
		}
		System.out.println(findRecipe(0));
	}

	public static long findRecipe(int cnt) {
		// * 탈출 조건 (모든 재료를 검토했기 때문에, 레시피가 완성됐다)
		if (cnt == N) {
			long sour = 1;
			long bitter = 0;
			boolean isEmpty = true;
			for (int i = 0; i < N; i++) {
				if (recipe[i]) {
					isEmpty = false;
					sour *= ingrds[i][0];
					bitter += ingrds[i][1];
				}
			}
			if(isEmpty) return Long.MAX_VALUE;
			else return Math.abs(sour - bitter);
		}
		// 00. cnt번째 재료를 넣을 것인가 말 것인가?
		recipe[cnt] = true;
		long recipe1 = findRecipe(cnt + 1);
		recipe[cnt] = false;
		long recipe2 = findRecipe(cnt + 1);
		
		// 01. 둘 중 더 차이가 작은 것을 반환한다.
		return Math.min(recipe1, recipe2);
	}
}
