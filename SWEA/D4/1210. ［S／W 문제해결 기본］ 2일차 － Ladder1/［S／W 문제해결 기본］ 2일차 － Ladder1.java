import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 마지막 행에서 2를 탐색하여 열(col) 정보 저장 2. 위로 이동 -> 99 행 ~ 0행까지 이동 2-1) 왼쪽이 1인 경우
 * 왼쪽으로 이동 => 다음칸이 경계를 넘어가거나 0일때까지 이동 2-2) 오른쪽이 1인 경우 오른쪽으로 이동 => 다음 칸이 경계를
 * 넘어가거나 0일때까지 이동 3. 이때의 열(col)을 출력
 * 
 * @author SSAFY
 * 
 */

public class Solution {
	static int[][] ladder;
	final static int N = 100; // 사다리 크기
	static int col; // 열 위치
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// System.setIn(new FileInputStream("src/recursion/input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			col = 0;
			tc = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(' ');
			ladder = new int[N][N];

			// ladder 값 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// step 01. 마지막 행에서 2를 탐색하여 열 col 정보 저장
			for (int c = 0; c < N; c++) {
				if (ladder[N - 1][c] == 2)
					col = c;
			}

			// step 02. 위로 이동 -> 99행 ~ 0행까지 이동
			for (int row = N - 1; row >= 0; row--) {
				// 2-1) 왼쪽이 1일 경우 왼쪽으로 이동
				if (col != 0 && ladder[row][col - 1] == 1)
					moveLeft(row);
				// 2-2) 오른쪽이 1인 경우 오른쪽으로 이동
				else if (col != N - 1 && ladder[row][col + 1] == 1)
					moveRight(row);

			}

			// step 03. 이 때의 열(col) 출력
			sb.append(col);
			System.out.println(sb.toString());
		}
	}

	private static void moveLeft(int row) {
		while (col - 1 >= 0 && ladder[row][col - 1] == 1)
			col--;
	}

	private static void moveRight(int row) {
		while (col + 1 < N && ladder[row][col + 1] == 1)
			col++;
	}

}