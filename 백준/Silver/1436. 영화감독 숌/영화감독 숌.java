import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 666
 * 1666
 * 5666
 * 6660
 * 6669
 * 16660
 * 16666
 *
 */
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int val = 665;
      while(N > 0) {
            val++;
            if(String.valueOf(val).contains("666")) {
                N--;
            }
        }
        System.out.println(val);
    }

}