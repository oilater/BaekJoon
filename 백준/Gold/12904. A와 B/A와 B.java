import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S, T;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        solution(T);
        System.out.println(0);
    }

    private static void solution(String s) {
        cnt++;

        if (s.length() == 0) return;

        if (s.equals(S)) {
            System.out.println(1);
            System.exit(0);
        }

        if (s.charAt(s.length() - 1) == 'A') {
            solution(s.substring(0, s.length() - 1));
        } else {
            solution(transformStr(s.substring(0, s.length() - 1)));
        }
    }

    private static String transformStr(String s) {
        String tmp = "";
        for (int i = s.length() - 1; i >= 0 ; i--) {
            tmp += String.valueOf(s.charAt(i));
        }

        return tmp;
    }
}