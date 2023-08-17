import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 배열 입력 받기
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        // 좋은 수 인지 찾기
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            long find = arr[i];
            int s = 0;
            int e = N-1;
            // 투포인터
            while(s < e) {
                if (arr[s] + arr[e] == find) {
                    if(s != i && e != i) {
                        cnt++;
                        break;
                    } else if (s == i) {
                        s++;
                    } else if (e == i) {
                        e--;
                    }
                } else if (arr[s] + arr[e] > find) {
                    e--;
                } else if (arr[s] + arr[e] < find) {
                    s++;
                }
            }
        }

        System.out.println(cnt);
    }


}