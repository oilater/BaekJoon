import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * K 가 N과 이미 같다면 0 출력
 * K 가 N보다 작다면 K-N 출력
 * K가 N보다 큰 경우에 bfs를 돌리자
 * dfs를 돌리기엔 시간이 너무 걸린다.
 * 이동할 수 있는 세 가지 경로 N-1 N+1 2*N
 * Q에 각각의 값을 넣어주고 그로 인해 파생되는 값들도 차례차례 넣어준다.
 * depth 별로 탐색하고, 탐색할 때마다 1씩 증가시켜준다.
 * N = K 가 될 때 bfs를 멈추고 depth를 출력한다.
 *
 */
public class Main {
    static int N, K;
    static final int MAX = 100001;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // K가 N과 같거나, N보다 작은 경우
        if (K <= N) {
            System.out.println(N - K);
            return;
        }

        // K가 N보다 크다면
        bfs();
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>(); // 큐 생성
        boolean[] visited = new boolean[MAX];

        q.offer(N);
        visited[N] = true;

        int time = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int curX = q.poll();        // 현재 위치

                // 종료조건
                if (curX == K) {
                    System.out.println(time);
                    return;
                }

                // 2 * X
                int num1 = curX * 2;
                if (num1 < MAX && !visited[num1]) {
                    q.offer(num1);
                    visited[num1] = true;
                }

                int num2 = curX + 1;
                if (num2 < MAX && !visited[num2]) {
                    q.offer(num2);
                    visited[num2] = true;
                }

                int num3 = curX - 1;
                if (num3 >= 0 && !visited[num3]) {
                    q.offer(num3);
                    visited[num3] = true;
                }
            }
            time++;
        }
    }
}