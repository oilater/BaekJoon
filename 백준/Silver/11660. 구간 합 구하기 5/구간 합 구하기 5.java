import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] arr;
	static int[][] S;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 4
		M = Integer.parseInt(st.nextToken()); // 3
		arr = new int[N + 1][N + 1];
		S = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				arr[i][j] = tmp;
				S[i][j] = S[i][j - 1] + S[i - 1][j] - S[i - 1][j - 1] + arr[i][j];
			}
		}

		for (int i = 0; i < M; i++) {
			int ans = 0;
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			ans = S[x2][y2] - S[x2][y1 - 1] - S[x1 - 1][y2] + S[x1 - 1][y1 - 1];

			System.out.println(ans);
		}
	}
}