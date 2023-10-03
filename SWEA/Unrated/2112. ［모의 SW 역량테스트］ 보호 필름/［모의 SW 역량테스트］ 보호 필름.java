import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 투입횟수의 최솟값을 구하는 문제
 * 1~D 중 약품을 넣어보며 다 넣었다면, 성능검사를 통과하는지 확인해야 한다.
 * 넣는 것은 부분집합으로 구할 수 있는데,
 * 그냥 부분집합을 이용하면 복잡하고 번거로워지므로
 * 조합 코드를 이용한 부분집합을 구현하자.
 * 넣을 수 있는 최대 범위는 K개까지 넣으면 되므로 1~K로 cnt 범위를 정해주자
 *
 * 넣는것이 실패하면 다시 돌아와야 하므로 map으로 복사해서 사용하자.
 * 실패할 시에는 arr[i]를 다시 참조하도록 하자.
 */
public class Solution {
    static int D, W, K; // K는 약품 투입의 최소 횟수

    static int[][] origin; // 원본 배열
    static int[][] map; // 복사 배열

    static int[] A = new int[20];
    static int[] B = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        A: for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            origin = new int[D][W];
            map = new int[D][W];


            // 원본 맵, 복사 맵 한번에 입력
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = origin[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // K가 1일 때는 약품을 투입하지 않아도 되므로 예외 처리 해주기
            if (K == 1) {
                sb.append("#").append(tc).append(' ').append(0).append('\n');
                continue A;
            }

            // 이제 해야 할 것은 약품을 차례대로 투입해보고 검사하는 것
            // 약품 투입은 조합코드를 이용한 부분집합을 이용하자
            // 최소 횟수를 정해야 하므로 하나씩 넣는것에서 부터 시작해서 K까지 넣어보자
            int cnt = 0; // 최소 횟수 (정답값)
            for (cnt = 0; cnt <= K; cnt++) {
                if(combination(0, 0, cnt)) {
                    sb.append("#").append(tc).append(' ').append(cnt).append('\n');
                    continue A;
                }
            }
        }
        System.out.println(sb);
    }

    private static boolean combination(int cnt, int start, int r) {
        if (cnt == r) { // 현재 개수만큼 투입이 완료되었다면 검사해줘야 함
            if(isPass()) return true; // 합격 기준 만족하면 true 반환
            return false; // 만족하지 못하면 맵을 되돌리고 false 반환
        }

        for (int i = start; i < D; i++) {
            map[i] = A;
            if (combination(cnt+1, i+1, r)) {
                return true;
            }

            map[i] = B;
            if (combination(cnt+1, i+1, r)) {
                return true;
            }

            map[i] = origin[i];
        }
        return false;
    }

    // 성능검사 메서드
    private static boolean isPass() {
        A: for (int i = 0; i < W; i++) { // W를 하나씩 돌면서 연속한 cnt가 K개가 되는지 검사
            int cnt = 1;
            for (int j = 0; j < D-1; j++) {
                if (map[j][i] == map[j+1][i]) {
                    cnt++;
                    if (cnt >= K) continue A;
                } else {
                    cnt = 1;
                }
            }
            return false;
        }
        return true;
    }
}