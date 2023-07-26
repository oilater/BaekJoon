import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int answer = 0;
			char[] arr = br.readLine().toCharArray();
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == 'O') {
					sum++;
					answer += sum;
				} else if (arr[i] == 'X') {
					sum = 0;
				}
			}
			System.out.println(answer);
		}
	}

}