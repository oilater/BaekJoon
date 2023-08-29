/**
 * N개의 도시 사이에 도로
 * 1km 당 1L
 * 각 도시에는 주유소 - 기름 충전
 * 주유소의 리터당 가격
 *
 * 생각나는 풀이
 * 주유비가 싸다면 최대한 많이 충전해놓으면 좋지 않을까?
 * 총 가야할 거리 : 6km
 *
 * 처음 출발:
 * 다음으로 가야할 키로수 만큼 기름을 넣어야 한다.
 * 근데 제일 싸다면? 아예 다 넣고 가는게 낫지 않을까?
 *
 * 그러니 총 거리를 알고 있어야 하고,
 * 다음에 올 주유소의 기름 가격을 알고 있어야 한다.
 * 기름 가격을 비교하고 가자
 *

 * 구현할 기능
 *
 * 주유비로 사용한 총 금액 money
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] distances;
    static int[] oilCosts;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시 개수
        distances = new int[N-1]; // 도시 간의 거리들
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) { // 거리 배열 채우기
            distances[i] = Integer.parseInt(st.nextToken());
        }

        oilCosts = new int[N]; // 각 도시의 주유 비용
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 주유 비용 채우기
            oilCosts[i] = Integer.parseInt(st.nextToken());
        }

        // 다음 주유소 가격이 현재보다 비싸다면 현재 금액으로 충전할 거리 누적
        // 근데 일단 2번 도시까진 가고 시작.

        long moneySum = distances[0] * oilCosts[0];
        for (int i = 1; i < N-1; i++) {
            if (oilCosts[i] >= oilCosts[i+1]) { // 현재 주유비가 다음 주유소의 주유비보다 비싸다면
                moneySum += oilCosts[i] * distances[i];
            } else { // 현재 주유비가 다음 주유소의 주유비보다 싸다면
                moneySum += oilCosts[i] * distances[i];
                oilCosts[i+1] = oilCosts[i]; // 다음 주유소 가격을 현재 주유소의 가격으로 변경
            }
        }

        System.out.println(moneySum);

    }
}