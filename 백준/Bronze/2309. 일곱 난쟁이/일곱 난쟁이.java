import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    static int[] arr;
    static int[] picked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[9];
        picked = new int[7];
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());

        }
        combination(0, 0);
    }

    private static void combination(int cnt, int start) {
        if(cnt == 7) {
            int sum = 0;
            for (int i: picked) {
                sum += i;
            }
            if(sum == 100) {
                Arrays.sort(picked);
                for (int el: picked) {
                    System.out.println(el);
                }
                System.exit(0);
            }
            return;
        }

        for (int i = start; i < 9; i++) {
            picked[cnt] = arr[i];
            combination(cnt+1, i+1);
        }
    }
}