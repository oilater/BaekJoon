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

		int day = (v - a) / (a - b);
		int rest = (v - a) % (a - b);

		if (rest != 0) {
			System.out.println(day + 2);
		} else {
			System.out.println(day + 1);
		}
	}
}
