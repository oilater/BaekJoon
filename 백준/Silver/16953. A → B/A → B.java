import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * A -> B
 * 2를 곱하거나
 * 1을 수의 가장 오른쪽에 추가하거나
 *
 * 매번 2를 곱하거나 1을 끝에 더해주거나 둘중 하나
 * 재귀
 * B와 같거나 커지면 종료
 * dfs는 너무 시간 오래걸릴듯
 * bfs로 풀자
 */
public class Main {
    static int A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());


        System.out.println(bfs(A));
    }

    private static long bfs(long n) {
        Queue<Long> que = new ArrayDeque<>();
        que.add(n);

        long cnt = 0;
        while(!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                long i = que.poll();
                if (i == B) return cnt+1;
                long n1 = i * 2;
                long n2 = i * 10 + 1;
                if (n1 <= B) que.add(n1);
                if (n2 <= B) que.add(n2);
            }
            cnt++;
        }
        return -1;
        }
    }