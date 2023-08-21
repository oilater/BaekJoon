import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 수빈이 현재점 N, 동생은 K
 * 수빈이 걷거나 순간이동 가능
 * X일 때 걷는다면 1초후에 X-1 또는 X+1 이동
 * 순간이동하면 1초 후 2*X로 이동
 * 동생을 찾을 수 있는 가장 빠른 시간은 몇 초 후?
 *
 * 입력
 * 수빈이의 위치 N과 동생의 위치 K
 *
 * 생각나는 풀이
 * BFS => X => X-1, X+1, 2 * X => 세 점에서 또 각자 타고 들어가기
 * N >= K 이면 시간은 N-K 초 => 출력하고 리턴
 * 아니라면 bfs 호출
 */
public class Main {
    static final int MAX = 100001;
    static int N, K;

    static boolean[] visited = new boolean[MAX];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N >= K) {
            System.out.println(N-K);
            return;
        }

        bfs(N); // 5
    }
    private static void bfs(int x) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(x); // 5
        visited[x] = true; // 방문 체크
        int time = 0; // 시간 계산
        while (!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int now = q.poll(); // 꺼내기

                if (now == K) {
                    System.out.println(time);
                    return;
                }

                int s1 = now - 1;
                if (s1 >= 0 && !visited[s1]) {
                    q.offer(s1);
                    visited[s1] = true;
                }


                int s2 = now + 1;
                if (s2 < MAX && !visited[s2]) {
                    q.offer(s2);
                    visited[s2] = true;
                }

                int s3 = now * 2;
                if (s3 < MAX && !visited[s3]) {
                    q.offer(s3);
                    visited[s3] = true;
                }

            }
            time++;
        }
    }


}