import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작시간: 8:08
 * 종료시간:
 * 풀이시간:
 *
 * 문제
 *
 * 생각나는 풀이
 * dfs로 풀자 - 가능할듯
 * Pipe 클래스
 * 끝점 r, c 좌표, 파이프의 모양 shape ('-', '|', '\' 중 하나)
 * 각 Pipe의 끝점 좌표가 도착점 좌표와 같다면 cnt ++
 * 도달할 수 없다면 0 출력 - 애초에 pipeCnt 0으로 초기화
 * 끝점을 기준으로 생각하자
 *
 * 방문처리는 어떻게 해야 할까?
 * visited 배열 true, false 체크
 * - 한쪽 방향으로만 탐색하니 방문 체크 필요 없다.
 * 벽 긁히는 지 검사는 어떻게?
 * - 가로일때는 오른쪽, 세로일땐 아래쪽,
 * - 대각선일 때는 현재 위치에서 오른쪽, 아래, 우하 대각에 벽이 있는지 검사해줘야 한다.
 */
public class Main {
    static class Pipe {
        int r, c, shape; // shape: 가로 - 0, 세로 - 1, 대각선 - 2

        public Pipe(int r, int c, int shape) {
            this.r = r;
            this.c = c;
            this.shape = shape;
        }
    }

    static int N;
    static int[][] map;

    static Pipe start;
    static Pipe end;
    static int pipeCnt; // 정답값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        start = new Pipe(0, 1, 0); // 시작 파이프 설정
        end = new Pipe(N - 1, N - 1, -1); // 마지막 모양은 상관 없으니 음수로 초기화

        bfs();
        System.out.println(pipeCnt);
    }

    private static void bfs() {
        Queue<Pipe> que = new ArrayDeque<>();
        que.add(start); // 시작 파이프 넣기

        while (!que.isEmpty()) {
            Pipe p = que.poll(); // 파이프 꺼내기
            int r = p.r;
            int c = p.c;
            int shape = p.shape;

            if (r == end.r && c == end.c) pipeCnt++; // 도착하면 pipeCnt++
            if (shape == 0) { // 현재 파이프 가로인 경우
                if (isInRange(r, c+1) && map[r][c+1] != 1) {
                    que.add(new Pipe(r, c+1, 0)); // 가로 연결
                }

                if (isInRange(r+1, c+1) && map[r][c+1] != 1 && map[r+1][c] != 1 && map[r+1][c+1] != 1) {
                    que.add(new Pipe(r+1, c+1, 2));
                }

            } else if (shape == 1) { // 세로인 경우
                if (isInRange(r+1, c) && map[r+1][c] != 1) {
                    que.add(new Pipe(r+1, c, 1)); // 세로 연결
                }

                if (isInRange(r+1, c+1) && map[r+1][c] != 1 && map[r][c+1] != 1 && map[r+1][c+1] != 1) {
                    que.add(new Pipe(r+1, c+1, 2));
                }
            } else { // 대각선인 경우
                if (isInRange(r, c+1) && map[r][c+1] != 1) {
                    que.add(new Pipe(r, c+1, 0)); // 가로 연결
                }

                if (isInRange(r+1, c) && map[r+1][c] != 1) {
                    que.add(new Pipe(r+1, c, 1)); // 세로 연결
                }

                if (isInRange(r+1, c+1) && map[r][c+1] != 1 && map[r+1][c] != 1 && map[r+1][c+1] != 1) {
                    que.add(new Pipe(r+1, c+1, 2));
                }
            }
        }


    }
    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}