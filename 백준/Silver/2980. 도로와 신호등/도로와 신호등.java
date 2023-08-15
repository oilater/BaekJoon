import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int D, R, G = 0;
    static int time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int time = 0;
        int now = 0;

        for (int test_case = 0; test_case < N; test_case++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); // 신호등 위치
            R = Integer.parseInt(st.nextToken()); // 빨간불 지속시간
            G = Integer.parseInt(st.nextToken()); // 초록불 지속시간

            // 차례대로 for 문을 돌려 이동하는 것이 아니라
            // 신호등까지의 거리를 시간에 더해주고 신호등에서 처리할 작업을 해주었다.

            time += D - now;
            now = D;

            int tmp = time % (R + G);
            if(tmp <= R) {
                time += R - tmp;
            }
        }
        // 마지막 신호등의 위치에서 도착점까지의 거리를 시간에 더해준다.
        time += L - now;
        System.out.println(time);
    }

}