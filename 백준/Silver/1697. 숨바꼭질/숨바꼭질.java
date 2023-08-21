import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 수빈이가 동생에게 이동할 수 있는 모든 경우의 수 구하기
 *
 * 수빈 < 동생
 * - 2*X  X+1   X-1 로 이동해보기
 *
 * 수빈 > 동생
 * - X-1 로 이동
 * 이 때 갈 수 있는 방법이 한 가지 뿐이므로
 * 수빈 - 동생 만큼 이동하기
 *
 * 수빈 == 동생
 * 현재까지 이동시간이 최소 시간이라면 저장
 */
public class Main {
    final static int MAX = 100001;

    static int N; // 수빈이 위치
    static int K; // 동생 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N >= K) {
            System.out.println(N - K);
            return;
        }

        bfs();
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX];

        q.offer(N);
        visited[N] = true;

        int time = 0; // 현재까지 걸린 시간
        while(!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int curX = q.poll(); // 현재 위치


                // 처음 K에 도달한 경우 지금까지의 시간이 최소시간이므로 출력 후 종료
                if (curX == K) {
                    System.out.println(time);
                    return;
                }

                //2 * X
                int num1 = 2 * curX;
                if (num1 < MAX && !visited[num1]) {
                    q.offer(num1);
                    visited[num1] = true;
                }

                // X + 1
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