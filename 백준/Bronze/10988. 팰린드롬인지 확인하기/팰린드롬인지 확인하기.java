import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split("");
		int answer = 1;
		for (int i = 0, j = arr.length - 1; i < arr.length; i++, j--) { 
			if(!arr[i].equals(arr[j])) {
				answer = 0;
				break;
			}
		}
		System.out.println(answer);
	}
}