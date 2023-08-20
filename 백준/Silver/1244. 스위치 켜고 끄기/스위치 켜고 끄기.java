import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 학생들 수 아직 입력 안받음
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (sex == 1) {
				for (int idx = num; idx <= N; idx += num) {

					arr[idx] = (arr[idx] + 1) % 2; // 쩐다

				}
			} else if (sex == 2) {
				int val = 1;
				int max = Math.min(N - num, num-1);
				arr[num] = (arr[num] + 1) % 2;
				while (val <= max) {
					if (arr[num - val] == arr[num + val]) {
						arr[num - val] = (arr[num - val] + 1) % 2;
						arr[num + val] = (arr[num + val] + 1) % 2;
					} else {
						break;
					}
					val++;
				}
			}

		}

		for (int r = 1; r < arr.length; r++) {
			sb.append(arr[r]).append(' ');
			if (r%20 == 0)
				sb.append('\n');
		}
		System.out.println(sb.toString());
	}

}