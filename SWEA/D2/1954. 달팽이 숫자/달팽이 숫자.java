import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 0,0 ~ 0,3 까지 1~4 1,3 ~ 3,3 까지 5~7 3,2 ~ 3,0 까지 8~10 2,0 ~ 1,0 까지 11, 12 1,0 ~
 * 1,1 까지 13, 14
 */

public class Solution {
	static int[][] arr;
	static int N;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			sb.append("#").append(test_case).append(' ').append("\n");
			arr = new int[N][N];
			arr[0][0] = 1;
			int direction = 0;
			int cnt = 1;
			int row = 0;
			int col = 0;

			while (cnt != N * N) {
				if (direction == 0 && col + 1 < N) {
					arr[row][col + dc[0]] = ++cnt;
					col++;

					if (col + 1 == N || arr[row][col + 1] != 0) {
						direction = 1;
						continue;
					}
				}

				if (direction == 1 && row < N) {
					arr[row + dr[1]][col] = ++cnt;
					row++;
					if (row + 1 == N || arr[row + 1][col] != 0) {
						direction = 2;
						continue;
					}
				}

				if (direction == 2 && col >= 0) {
					arr[row][col + dc[2]] = ++cnt;
					col--;

					if (col - 1 < 0 || arr[row][col - 1] != 0) {
						direction = 3;
						continue;
					}
				}

				if (direction == 3 && row > 0) {
					arr[row + dr[3]][col] = ++cnt;
					row--;

					if (row - 1 < 0 || arr[row - 1][col] != 0) {
						direction = 0;
						continue;
					}
				}

			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(arr[i][j]).append(' ');
				}
				if (i != N - 1)
					sb.append("\n");
			}
			System.out.println(sb.toString());
		}

	}

}