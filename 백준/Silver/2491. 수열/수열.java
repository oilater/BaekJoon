import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수열의 길이
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // 수열 배열 생성
        StringTokenizer st =  new StringTokenizer(br.readLine());
        // 배열 입력 받기
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 1;
        int cnt = 1;
        // 오름차순 확인
        for (int i = 0; i < N - 1; i++) {
            if(arr[i]<= arr[i+1]) {
                cnt++;
            } else {
                cnt = 1;
            }

            max = Math.max(cnt, max);
        }


        // 카운트 초기화
        int cnt2 = 1;
        // 내림차순 확인
        for (int i = 0; i < N - 1; i++) {
            if(arr[i] >= arr[i+1]) {
                cnt2++;
            } else {
                cnt2 = 1;
            }
            max = Math.max(cnt2, max);
        }

        // 둘 중 더 많은 수 출력
        System.out.println(max);
    }
}