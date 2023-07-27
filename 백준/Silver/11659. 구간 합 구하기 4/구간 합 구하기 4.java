import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	static int[] sumArr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 5
		M = Integer.parseInt(st.nextToken()); // 3
		arr = new int[N];
		int sum = 0;
		sumArr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
			sumArr[i] = sum;
		}

		for (int t = 0; t < M; t++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()); // 1
			int j = Integer.parseInt(st.nextToken()); // 3
			if (i - 2 < 0) {
				System.out.println(sumArr[j - 1]);
			} else {
				System.out.println(sumArr[j - 1] - sumArr[i - 2]);
			}
		}
	}
}