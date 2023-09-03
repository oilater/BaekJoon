import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0 , 1}; // 우상 우 우하

    static char[][] arr;
    static int R, C;

    static int pipeLineCnt; // 최대 파이프의 개수 정답값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        // step 01. 각 행에 대한 파이프 라인 설치 시도
        for (int r = 0; r < R; r++) {

            if(dfs(r, 0)) pipeLineCnt++;
        }

        System.out.println(pipeLineCnt);
    }

    /**
     * 현재 좌표 r, c에서 파이프를 세방향(우상, 우, 우하)으로 설치해보고
     * 다음 좌표(nr, nc) 기준 세 방향 파이프 설치는 재귀로 넘김
     * @param r 현재 좌표 행
     * @param c 현재 좌표 열
     * @return (r, c) 좌표에서 파이프 설치를 쭉 시도했을 때 파이프 라인 구추겡 성공한다면 true 반환
     */
    private static boolean dfs(int r, int c) {
        // step 03. 마지막 열이라면 파이프라인 설치 성공
        if (c == C-1) {
            return true;
        }

        // step02. 세가지 방향으로 파이프 설치하기
        for (int d = 0; d < 3; d++) {
            // 선택된 방향의 다음 좌표
            int nr = r + dr[d];
            int nc = c + 1;

            // 경계 벗어나거나 빈칸이 아니라면 다음 방향
            if(nr < 0 || nr >= R || arr[nr][nc] != '.') continue;

            // 파이프 설치
            arr[nr][nc] = '-';

            // 다음 좌표 기준 세 방향에 대한 파이프 설치 시도는 재귀로 넘김
            if (dfs(nr, nc)) return true;
        }
        return false;
    }
}