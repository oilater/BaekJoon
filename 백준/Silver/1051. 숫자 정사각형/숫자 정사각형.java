import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * 문제
 * 꼭짓점에 쓰여있는 수가 모두 같은 가장 큰 정사각형을 찾는 프로그램을 작성하시오
 *
 * 조건
 * 1 <= N, M <= 50
 * 숫자가 작으니 브루트포스로 풀어도 된다.
 *
 * 생각나는 풀이
 * 음 일단 정사각형을 찾는 함수를 만들자
 * 이거 재귀로 풀고 싶은데 일단 for문으로 풀어보고 재귀를 공부하자
 *
 */
public class Main {
    static int N, M;
    static int[][] arr;
    static int max; // 가장 큰 정사각형의 꼭짓점 합을 담아줄 변수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        // 배열 입력
        for (int i = 0; i < N; i++) {
            String cStr = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = cStr.charAt(j) - '0';
            }
        }

        // 정사각형 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                findSquare(i, j, 0);
            }
        }

        System.out.println(max);
    }

    private static void findSquare(int r, int c, int length) {
        int width = 0;
        if(!isInRange(r+length, c+length)) return;
        if(isSame(r, c, length)) {
            width += (length + 1)*(length + 1);
        }
        max = Math.max(max, width);
        findSquare(r, c, length+1);
    }

    private static boolean isSame(int r, int c, int length) {
        return arr[r][c] == arr[r+length][c] && arr[r][c] == arr[r][c+length] && arr[r][c] ==arr[r+length][c+length];
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}