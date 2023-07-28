import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	static int N;
	static int start;
	static int end;
	static int cnt = 1;
	static int sum = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine()); // 5
		start = 1;
		end = 1;

		while (N != end) {
			if (sum == N) {
				cnt++;
				end++;
				sum += end;
			} else if (sum > N) {
				sum -= start;
				start++;
			} else {
				end++;
				sum += end;
			}
		}
		System.out.println(cnt);
	}
}