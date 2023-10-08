import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C;
    static int[][] honey;

    static int maxRevenue;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 맵 길이
            M = Integer.parseInt(st.nextToken()); // 일꾼의 꿀통 길이
            C = Integer.parseInt(st.nextToken()); // 채취할 수 있는 꿀의 최대 양
            honey = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    honey[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxRevenue = 0;

            // step 01. 두 일꾼의 벌통 선택
            for (int i = 0; i < N * N; i++) { // i: 첫번째 선택한 벌통 그룹의 시작 인덱스
                int oneR = i / N; // 행
                int oneC = i % N; // 열
                if (oneC > N-M) continue;

                // step 02. 첫번째 선택한 벌통 그룹의 최대 이익 구하기
                int firstRevenue = getMaxRevenue(oneR, oneC, 0, 0, 0);

                for (int j = i + M; j < N * N; j++) {
                    int twoR = j / N;
                    int twoC = j % N;
                    if (twoC > N - M) continue;

                    int secondRevenue = getMaxRevenue(twoR, twoC, 0, 0, 0);
                    maxRevenue = Math.max(maxRevenue, firstRevenue + secondRevenue);
                }
            }

            sb.append("#").append(tc).append(' ').append(maxRevenue).append('\n');
        }
        System.out.println(sb);
    }

    // 따로 배열을 만들어줄 것이 아니라 재귀함수의 매개변수를 이용할 수 있다.
    // 시작점을 안다면, 재귀를 통해 M개만큼 오른쪽으로 이동하며 벌통의 꿀들을 채집할 수 있다.
    private static int getMaxRevenue(int sr, int sc, int idx, int sum, int revenue) {
        if (sum > C) return 0;
        if (idx == M) return revenue;

        int curHoney = honey[sr][sc + idx];

        // idx 번째 벌통 선택
        int A = getMaxRevenue(sr, sc, idx+1, sum+curHoney, revenue+curHoney * curHoney);

        // idx 번째 벌통 비선택
        int B = getMaxRevenue(sr, sc, idx+1, sum, revenue);

        // 두 경우의 이익 중 큰 값을 반환
        revenue = Math.max(A, B);

        return revenue;
    }
}