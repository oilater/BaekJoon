import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitOption;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 회전 순서 정함
 * 하나씩 for문으로 불러와서 회전 실행
 * 회전해서 넣으려면? 새로운 배열이 필요 -> 원래 배열 복사 clone 이용
 * 범위에 따라 배열 시계방향으로 회전하기
 *
 * 델타 생성 - 하우상좌
 * 첫값 저장 후 아래에서 하나씩 땡겨오기
 * dir 현재 방향 지정 0 1 2 3 하 우 상 좌
 * dir < 4 까지 돌기 while의 조건
 * 첫 값은: r-s, c-s
 * 근데 안쪽부터 바깥쪽까지 돌아야 하니까
 * s의 범위를 지정해야 함
 * o : 1 ~ s
 * nr, nc가 범위를 벗어나면 방향 바꿔주기 그전까진 계속 가기
 * r, c+1 에 저장했던 첫값 temp 넣어주기
 */
public class Main {
    static int N, M, K; // K는 회전 연산의 개수
    static int[][] arr;
    static int[][] copy;

    // delta 하 우 상 좌
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Node {
        int r, c, s;

        public Node(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static ArrayList<Node> nodeList;
    static ArrayList<Node> pickedList;
    static boolean[] visited;

    static int ans = 0; // 정답값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        arr = new int[N+1][M+1];
        copy = new int[N+1][M+1];
        nodeList = new ArrayList<>();
        pickedList = new ArrayList<>();
        visited = new boolean[K];
        for (int i = 1; i <= N; i++) { // 배열 입력받기
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            nodeList.add(new Node(r, c, s));
        }

        permutation(0); // 순열 돌려~

        System.out.println(ans); // 출력

    }

    // 배열을 다 돌렸다면 minCnt 찾아줘야 함
    private static void getMinCnt() {
        int attribute = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += copy[i][j];
            }
            attribute = Math.min(sum, attribute); // 배열값에 각 행의 합의 최솟값 넣어주기
        }
        ans = Math.min(ans, attribute); // 정답값 업데이트
    }

    // Node : r, c, s
    private static void copyArr () {
        for (int i = 1; i <= N; i++) {
            System.arraycopy(arr[i], 1, copy[i], 1, M);
        }
    }

    private static void rotate(Node node) {

        for (int s = 1; s <= node.s; s++) {
            int r = node.r - s;
            int c = node.c - s;
            int dir = 0; // 현재 방향

            int tmp = copy[r][c]; // 첫 값 저장

            while (dir < 4) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if(nr >= node.r - s && nr <= node.r + s && nc >= node.c - s && nc <= node.c + s) { // 범위 안에 있다면
                    copy[r][c] = copy[nr][nc];
                    r = nr;
                    c = nc;
                } else { // 범위를 벗어나면 방향 전환
                    dir++;
                }
            }
            copy[r][c+1] = tmp; // 첫값 넣기
        }
    }

    private static void permutation(int cnt) {
        if (cnt == K) {
            copyArr(); // 배열 복사
            // 정해진 회전 순서로 배열을 돌리자
            for (int i = 0; i < K; i++) { // 2개중 하나씩 회전 함수 실행
                rotate(pickedList.get(i));
            }

            getMinCnt(); // 완성된 배열의 최솟값 찾기
            return;
        }

        for (int i = 0; i < K; i++) {
            if(!visited[i]) {
                visited[i] = true;
                pickedList.add(nodeList.get(i));
                permutation(cnt + 1);
                visited[i] = false;
                pickedList.remove(nodeList.get(i));
            }

        }
    }
}