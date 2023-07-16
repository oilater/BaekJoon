import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().toUpperCase().split("");
		String[] al = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
		String max = "";
		int temp = 0;
		for (int i = 0; i < al.length; i++) {
			int count = 0;
			for (int j = 0; j < arr.length; j++) {
				if (al[i].equals(arr[j])) {
					count++;
				}
			}
			if (count > temp) {
				temp = count;
				max = al[i];
			} else if (count == temp) {
				max = "?";
			}
		}
		System.out.println(max);
	}
}