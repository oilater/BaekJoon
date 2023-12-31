import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제
 * 시작시간: 10:57
 * 종료시간: 12:10
 * 풀이시간: 1h 13m
 *
 * 모든 바이ㅓ스는 1초마다 4방으로 퍼짐
 * 단, 번호가 낮은 종류의 바이러스부터 먼저 증식함
 * 특정 칸에 이미 바이러스 있다면 그곳은 증식 못함
 *
 * S초 지난 후에 x,y에 존재하는 바이러스의 종류 출력
 * 해당 위치에 존재하지 않는다면 0 출력
 * x, y는 행 , 열
 * 가장 위쪽은 1,1
 *
 * 생각나는 풀이
 * level별 dfs
 * time 증가시키면서 time == T  일때 x,y 확인
 * visited 배열 할 필요 없을 듯 -> 해당 칸에 숫자 있으면 증식 불가
 *
 * 가장 번호가 낮은 것부터 어떻게 꺼낼까?
 * priority Queue 쓰면 되지 않을까?
 * ㄴ 안됨 -> 작은것만 계속 뽑혀서 1로 도배됨
 * -> list를 이용해서 queue를 바이러스 type 크기 순으로 세팅해주자
 *
 */
public class Main {
    // 4방향 델타 배열
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 바이러스 클래스
    static class Virus {
        int r, c, type;
        public Virus(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    static int N, K; // N개의 줄, K이하의 자연수
    static int S, R, C; // S : 시간, X : 해당 위치 행, Y : 해당 위치 열
    static int[][] map; // 시험관

    static Queue<Virus> que = new ArrayDeque<>();
    static List<Virus> virusList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1]; // 맵 생성
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input; // 맵 입력
                if (input > 0) virusList.add(new Virus(i, j, input)); // 0보다 크면 바이러스이니 pq에 넣기
            }
        }

        Collections.sort(virusList, (a, b) -> a.type - b.type); // 작은 바이러스 순으로 정렬
        for (int i = 0; i < virusList.size(); i++) {
            que.add(virusList.get(i)); // 작은 바이러스부터 큐에 추가
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        if (S == 0) { // S가 0이라면 bfs를 돌 필요가 없음
            System.out.println(map[R][C]);
            return;
        }

        bfs();
        System.out.println(map[R][C]);
    }

    private static void bfs() {
        int time = 0;
        while (!que.isEmpty()) {
            int size = que.size(); // 현재 큐의 사이즈
            while (size-- > 0) {
                Virus virus = que.poll();
                int r = virus.r; // 현재 바이러스 행
                int c = virus.c; // 현재 바이러스 열
                int type = virus.type; // 현재 바이러스 종류

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] != 0) continue;
                    map[nr][nc] = type; // 바이러스 확산
                    que.add(new Virus(nr, nc, type)); // pq에 이동한 곳의 바이러스 넣어주기
                }
            }

            time++;
            if (time == S) return;
            setQueue(size);
        }
    }

    private static void setQueue(int size) {
        virusList.clear(); // 리스트 초기화
        for (int i = 0; i < size; i++) {
            virusList.add(que.poll()); // 리스트에 큐에 들어있는것 빼서 하나씩 넣기
        }
        Collections.sort(virusList, (a, b) -> a.type - b.type); // 리스트 정렬
        for (int i = 0; i < virusList.size(); i++) {
            que.add(virusList.get(i)); // 큐에 작은 바이러스부터 차례대로 추가
        }
    }
}


/**
 * 어려웠던 점
 * 작은 바이러스부터 어떻게 돌게 할까 고민
 * pq를 생각했지만 그렇게 되면 계속 1만 나옴
 *
 * 해결책) list를 만들어 sort한 후 다시 queue에 추가함
 * 이 작업을 위에서 해야 하나 아래에서 해야 하나 고민됐음
 *
 * time == S 일때 정답을 출력하고 return 했더니
 * 시간이 10인 경우, 그전에 큐가 먼저 다 빠져버려서 답이 안나오는 반레가 생김
 * 0초는 따로 빼서 처리해야 하는걸까?
 * setQueue 메서드로 빼면 size 전달해줘야 함?
 */