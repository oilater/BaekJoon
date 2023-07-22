import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		int left = 0, right = 0;
		int count = 0;
		loop:
		for (int i = 1; i <= x; i++) {
			if (i % 2 == 0) {
				for (int j = 1, k = i; j <= i; j++, k--) {
					count++;
					if (x == count) {
						left = j;
						right = k;
						break loop;
					}
				}
			} else {
				for (int j = i, k = 1; j >= 1; j--, k++) {
					count++;
					if (x == count) {
						left = j;
						right = k;
						break loop;
					}
				}
			}
		}
		System.out.println(left + "/" + right);
	}
}