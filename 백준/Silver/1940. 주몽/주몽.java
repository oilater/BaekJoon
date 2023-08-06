import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int S = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		int cnt = 0;
		int start = 0;
		int end = N - 1;

		while (start < end) {
			if (arr[start] + arr[end] > S) {
				end--;
			}

			else if (arr[start] + arr[end] < S) {
				start++;
			}

			else {
				cnt++;
				start++;
				end--;
			}
		}
		System.out.println(cnt);
		br.close();
	}

}