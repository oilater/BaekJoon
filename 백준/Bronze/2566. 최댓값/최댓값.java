import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] board = new int[10][10];
		int max = 0, c = 1, r = 1;
		for (int i = 1; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(max < board[i][j]) {
					max = board[i][j];
					c = i;
					r = j;
				}
			}
		}
		System.out.println(max);
		System.out.println(c + " " + r);
	}
}