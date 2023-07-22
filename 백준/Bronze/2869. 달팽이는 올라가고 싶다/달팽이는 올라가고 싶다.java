import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		int count = 0;
		int temp = 0;
		if (a == v) {
			System.out.println(1);
		} else {
			while (true) {
				count = v / a;
				temp += count;
				v = v - count * a + count * b;
				if (a >= v) {
					System.out.println(temp + 1);
					break;
				}
			}
		}
	}
}
