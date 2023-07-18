import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int count = 0;
		for (int i = 0; i < n; i++) {
			String str = sc.nextLine();
			loop:
			for (int j = 0; j < str.length(); j++) {
				int a = str.indexOf(str.charAt(j));
				int b = str.lastIndexOf(str.charAt(j));
				for (int k = a; k <= b; k++) {
					if (str.charAt(k) != str.charAt(j)) {
						count--;
						break loop;
					} 
				}
				
				
			}
			count++;
		}
		System.out.println(count);
	}
}