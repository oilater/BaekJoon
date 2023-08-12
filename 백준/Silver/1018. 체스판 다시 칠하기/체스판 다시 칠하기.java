import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int cntB, cntW;
    static int ans = Integer.MAX_VALUE;
    static char[][] arr;
    static char[][] chessStartWhite = new char[8][8];
    static char[][] chessStartBlack = new char[8][8];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N+1][M+1];

        // 배열 입력
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j-1);
            }
        }

        // 흰색으로 시작하는 체스판 만들기
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessStartWhite[i][j] = (i+j) % 2 == 0 ? 'W' : 'B';
            }
        }

        // 검정으로 시작하는 체스판 만들기
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessStartBlack[i][j] = (i+j) % 2 == 0 ? 'B' : 'W';
            }
        }

        // 시작점 범위 설정 1 ~ N-7 , 1 ~ M-7
        for (int i = 1; i <= N-7; i++) {
            for (int j = 1 ; j <= M-7; j++) {
                    int count = search(i, j); // 시작점이 범위 안에 있으면 체스판 탐색 시작
                    ans = Math.min(ans, count);
            }
        }

        // 출력
        System.out.println(ans);
    }

    // 8x8 체스판을 탐색해서 페인트 칠할 개수를 구해주는 메서드
    private static int search(int r, int c) {
        int tmpCnt= 0;
        cntB = 0; // 시작점이 B일 때 페인트 칠해야 하는 개수
        cntW = 0; // 시작점이 W일 때 페인트 칠해야 하는 개수

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int newR = r + i; // 포인터 열
                int newC = c + j; // 포인터 행

                if(arr[newR][newC] != chessStartWhite[i][j]) {
                    cntW++;
                }

                if(arr[newR][newC] != chessStartBlack[i][j]) {
                    cntB++;
                }

            }
        }
        tmpCnt = Math.min(cntW, cntB);
        return tmpCnt;
    }
}