import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] arr = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" };

		String st = sc.nextLine();
		
		for (int i = 0; i < arr.length; i++) {
			st = st.replace(arr[i], "!");
		}
		System.out.println(st.length());
	}
}