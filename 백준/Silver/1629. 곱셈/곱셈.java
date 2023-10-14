import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long A, B, C; // A를 B번 곱한 수를 C로 나눈 나머지
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(pow(A, B));
    }

    private static long pow(long A, long exponent) {
        if (exponent == 1) {
            return A % C; // 지수가 1이면 A를 그대로 리턴
        }

        long tmp = pow(A, exponent / 2);

        if (exponent % 2 == 1) { // 지수가 홀수인 경우
            return (tmp * tmp % C) * A % C;
        }
        return tmp * tmp % C; // 짝수일 경우
    }
}