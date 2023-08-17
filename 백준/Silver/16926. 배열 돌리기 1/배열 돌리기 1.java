import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int[][] arr;

    static int N, M, R;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1];
        // 배열 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            rotate();
        }
        //출력
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);


    }

    // 전체 1회전 메서드
    private static void rotate() {
        int cnt = Math.min(N, M) / 2;
        for (int s = 1; s <= cnt; s++) {
            int r = s;
            int c = s;
            int dir = 0;

            int temp = arr[r][c];
            int temp2 = 0;

            while(dir < 4) {
                // 이동할 좌표
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                // 이동할 좌표가 경계를 벗어나지 않는다면
                if(nr >= s && nr <= N -s + 1 && nc >= s && nc <= M - s +1) {
                    // 실제 이동
                    r = nr;
                    c = nc;

                    // 값 변경 작업
                    temp2 = arr[r][c]; // 이동한 값 temp2 에 받아놓기
                    arr[r][c] = temp;
                    temp = temp2;
                } else {
                    dir++;
                }
            }
        }
    }

}