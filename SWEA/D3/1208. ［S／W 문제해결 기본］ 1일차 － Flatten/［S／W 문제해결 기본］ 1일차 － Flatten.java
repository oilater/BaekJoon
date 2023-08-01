import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int test_case;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] arr = new int[100];
		// 테스트 케이스
		for (test_case = 1; test_case <= 10; test_case++) {

			st = new StringTokenizer(br.readLine());
			// 덤프 값
			int dump = Integer.parseInt(st.nextToken());
			// 값 채우기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 재귀
			recursive(dump, arr);

		}
	}

	public static void recursive(int dump, int[] arr) {
		if (dump == 0) {
			System.out.printf("#%d %d%n", test_case, arr[99] - arr[0]);
			return;
		}

		Arrays.sort(arr);
		arr[99] -= 1;
		arr[0] += 1;
		Arrays.sort(arr);
		recursive(dump - 1, arr);

	}
}