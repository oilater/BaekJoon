import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("");
		int time = 0;
		for(String a : s) {
			if(a.equals("A")|| a.equals("B") || a.equals("C")) time += 3;
			if(a.equals("D")|| a.equals("E") || a.equals("F")) time += 4;
			if(a.equals("G")|| a.equals("H") || a.equals("I")) time += 5;
			if(a.equals("J")|| a.equals("K") || a.equals("L")) time += 6;
			if(a.equals("M")|| a.equals("N") || a.equals("O")) time += 7;
			if(a.equals("P")|| a.equals("Q") || a.equals("R") || a.equals("S")) time += 8;
			if(a.equals("T")|| a.equals("U") || a.equals("V")) time += 9;
			if(a.equals("W")|| a.equals("X") || a.equals("Y") || a.equals("Z")) time += 10;
		}
		System.out.println(time);
	}
}
