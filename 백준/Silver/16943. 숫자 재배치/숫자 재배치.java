import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 조합일까 순열일까?
 * 1122라는 숫자가 있다고 쳐보자
 * 조합 아닐까
 * visited 배열의 의미가 크게 없어보인다.
 */
public class Main {
    static int[] A; // A 배열
    static int B;
    static boolean[] visited; // 방문 확인 배열
    static int[] picked;
    static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] tmp =  st.nextToken().toCharArray();
        visited = new boolean[tmp.length];
        picked = new int[tmp.length];
        A = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            A[i] = tmp[i] - '0';
        }
        B = Integer.parseInt(st.nextToken());

        permutation(0);
        System.out.println(ans);

    }

    public static void permutation(int cnt) {

        if(cnt == A.length) {
            int num = 0;
            int tmp = picked.length - 1;
            for (int i = 0; i < picked.length; i++) {
                num += picked[i] * Math.pow(10, tmp--);
            }
            
            if(num < B) ans = Math.max(ans, num);
            return;
        }


        for (int i = 0; i < A.length; i++) {
            if(visited[i]) continue;
            // C의 첫자리에 0이 오지 못하게 막음
            if(cnt == 0 && A[i] == 0) continue;

            picked[cnt] = A[i];
            visited[i] = true;
            permutation(cnt+1);
            visited[i] = false;
        }
    }
}