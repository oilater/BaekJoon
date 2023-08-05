import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int N;
	public static void main(String[] args) throws IOException {		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		for (int test_case = 1; test_case <= tc; test_case++) {
			N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			int nowR = 0; // 현재 열
			int nowC = 0; // 현재 행
			int nowDir = 0; // 현재 방향
			
			for (int i = 1; i <= N*N; i++) {
				arr[nowR][nowC] = i; // 배열에 1부터 N^2까지 넣어줌
				
				int nextR = nowR + dr[nowDir]; // 다음 열 - 현재 방향 0 오른쪽
				int nextC = nowC + dc[nowDir]; // 다음 행
				if (isInRange(nextR, nextC) && arr[nextR][nextC] == 0) {
					nowR = nextR;
					nowC = nextC;
				} else {
					nowDir = (nowDir + 1) % 4;
					nowR += dr[nowDir];
					nowC += dc[nowDir];
				}
			}
			
			sb.append('#').append(test_case).append('\n');
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					sb.append(arr[i][j]).append(' ');
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb); // 출력
	}
	private static boolean isInRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

}
