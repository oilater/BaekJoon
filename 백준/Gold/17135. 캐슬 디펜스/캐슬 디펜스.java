import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dr = {0, -1, 0};
    static int[] dc = {-1, 0, 1};

    static int N, M, D;
    static int[][] origin; // 격자판
    static int[][] map; // 복사할 판
    static boolean[][] visited; // 방문 배열

    static int[] hunters = new int[3]; // 궁수 위치 (행이 중요)

    static int kill; // 현재 조합의 킬 수
    static int maxKill; // 최대 킬 수

    static class Node {
        int r, c, dis;
        public Node (int r, int c, int dis) {
            this.r = r;
            this.c = c;
            this.dis = dis;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        map = new int[N][M];

        // 맵 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(maxKill); // 제거한 최대 적 수 출력

    }

    // 조합으로 궁수의 위치 선정
    private static void combination(int start, int cnt) {
        if (cnt == 3) { // 새로운 조합이 완성되었다면
            arrayCopy(); // 원본 맵 복사해 초기화
            kill = 0; // 킬 수 초기화
            // 궁수는 한번에 하나의 적만 잡을 수 있다
            for (int i = 0; i < N; i++) { // 사이클이 N번 진행되면 모든 적이 격자판에서 제외됨
                shoot(); // 궁수 공격
                move(); // 적 이동
            }

            maxKill = Math.max(maxKill, kill);
            return;
        }
        // 조합 만들기
        for (int i = start; i < M; i++) {
            hunters[cnt] = i;
            combination(i + 1, cnt + 1);
        }
    }

    private static void shoot() {
        List<Node> enemies = new ArrayList<>();
        A: for (int hunterC : hunters) { // 궁수 한 명의 차례에 할 일
            Queue<Node> q = new ArrayDeque<>(); // 큐 생성
            visited = new boolean[N][M]; // 방문 배열 초기화

            q.add(new Node(N-1, hunterC, 1)); // 큐에 넣기
            visited[N-1][hunterC] = true;
            while(!q.isEmpty()) { // 큐가 빌 때까지
                Node n = q.poll();
                int r = n.r;
                int c = n.c;
                int dis = n.dis; // 궁수로부터 현재 좌표까지의 거리

                if (map[r][c] == 1) {
                    enemies.add(n);
                    continue A; // 다음 궁수 차례로 이동
                }

                for (int d = 0; d < 3; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    // 새 위치 검사
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (visited[nr][nc]) continue;
                    if (dis + 1 > D) continue;

                    q.add(new Node(nr, nc, dis + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        
        // 적 죽이기
        for (Node e: enemies) {
            if (map[e.r][e.c] == 1) {
                map[e.r][e.c] = 0;
                kill++;
            }
        }
    }

    // 적 이동하기
    private static void move() {
        for (int i = N-2; i >= 0; i--) {
            map[i+1] = map[i];
        }
        map[0] = new int[M];
    }


    private static void arrayCopy() {
        for (int i = 0; i < N; i++) {
            map[i] = origin[i].clone();
        }
    }

}