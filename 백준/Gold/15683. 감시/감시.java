import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 상 우 하 좌
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    // CCTV 번호 별 델타 (0번 인덱스는 비워줌)
    // dr[2][0][d] => 중복순열을 뽑을 때 가운데 숫자를 뽑아줘야 함 -> 방향이 결정되니까
   static int[][][] dir = {
            {}, // 미사용 인덱스
            { {0}, {1}, {2}, {3}  },
            { {0, 2}, {1, 3} },
            { {0, 1}, {1, 2}, {2, 3}, {3, 0} },
            { {0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
            { {0, 1, 2, 3}}
    };

    static int N, M;
    static int[][] origin; // 원본 배열
    static int[][] map; // 복사할 배열

    static class CCTV {
        int r, c, number;
        int dir; // 결정된 방향
        int dirCnt = 4;

        public CCTV (int r, int c, int number) {
            this.r = r;
            this.c = c;
            this.number = number;
            if (number == 2) {
                dirCnt = 2;
            } else if (number == 5) {
                dirCnt = 1;
            }
        }
    }

    static List<CCTV> cctvs = new ArrayList<>(); // cctv의 위치를 담은 리스트

    static int minBlackArea = Integer.MAX_VALUE; // 최소 사각 지대 (정답값)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0 && num != 6) { // CCTV 찾으면 위치 정보(r, c)를 리스트에 담아줌
                    cctvs.add(new CCTV(i, j, num));
                }
                origin[i][j] = num; // 원본 맵 입력 받기
            }
        }

        setDirectionOfCCTV(0); // cctv의 방향을 정해줄 순열 메서드 호출 - 중복순열이라 visited 배열 필요 없음

        System.out.println(minBlackArea); // 결과 출력
    }

    // cctvDir 은 3차원 배열의 가운데에 들어갈 수
    // 순열을 왜 돌리나? cctv 중 누가 먼저 해도 상관없어 방향값 찾으려고 돌리는거지
    private static void setDirectionOfCCTV(int idx) {
        if (idx == cctvs.size()) {
            arrayCopy(); // 배열 복사
            for (CCTV cctv: cctvs) {
                watch(cctv); // cctv 하나씩 탐색
            }

            // 사각지대 개수 cnt
            int curBlackArea = getCurBlackArea();

            // 최소 사각지대 개수 cnt
            minBlackArea = Math.min(minBlackArea, curBlackArea);

            return;
        }

        CCTV cctv = cctvs.get(idx); // 방향을 결정할 cctv 정보

        // dir는 idx번째의 cctv가 바라볼 방향
        for (int dir = 0; dir < cctv.dirCnt; dir++) {
            cctv.dir = dir; // cctv 방향 결정
            setDirectionOfCCTV(idx + 1); // 다음 cctv의 방향 설정
        }
    }

    private static void watch(CCTV cctv) {
        // dirCase[cctv번호][선택한 방향]
        // 2번 cctv에 0번 방향이라면 상, 하 델타 인덱스를 뽑아냄
        for (int dir : dir[cctv.number][cctv.dir]) {
            int r = cctv.r;
            int c = cctv.c;
            while (true) {
                r += dr[dir];
                c += dc[dir];

                if(r < 0 || r >= N || c < 0 || c >= M || map[r][c] == 6) break;
                map[r][c] = -1;
            }
        }
    }


    private static int getCurBlackArea() {
        int blackArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) blackArea++;
            }
        }
        return blackArea;
    }



    // 배열 복사 메서드
    static void arrayCopy() {
        for (int i = 0; i < N; i++) {
            map[i] = origin[i].clone();
        }
    }
}