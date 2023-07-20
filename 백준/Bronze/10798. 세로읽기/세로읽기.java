import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] arr = new char[15][5];

		for (int t = 0; t < 5; t++) {
			char[] list = br.readLine().toCharArray();
			for (int i = 0; i < list.length; i++) {
				arr[i][t] = list[i];	
			}
		}
		String answer = "";
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] == '\0') continue;
				answer += arr[i][j];
			}
		}

		System.out.println(answer.trim());
	}

}