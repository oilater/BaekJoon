import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 이해
 * 가로, 세로 방향으로 1cm 마다 점선 그어짐
 * 한번 자를 때는 끝까지 자르기
 * 그렇게 잘랐을 때 가장 큰 종이 조각의 넓이를 구해라
 *
 * 입력
 * 첫줄: 가로 세로 길이   최대 100cm
 * 둘째줄: 칼로 잘라야 하는 점선의 개수
 * 셋째~마지막줄: 점선이 입력됨
 * 가로로 자르는 건 0, 점선번호
 * 세로로 자르는 건 1, 점선번호
 *
 * 생각나는 풀이
 * 가로점선 3, 2
 * 세로점선 4
 * 가로 - 1 * 세로 , 전체 - 가로 - 1 * 세로 각각 저장
 * 각각 가로, 세로 리스트에 자르는 위치 입력받아놓기
 * 가로 : 열 2, 3
 * 세로 : 4 , 6
 * 넓이 가로 끼리의 차 = 열의 차 = 2, 1 5 이런식으로 저장
 * 세로끼리의 차 = 행의 차 = 4 2 4
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        List<Integer> tmpRList = new ArrayList<>();
        List<Integer> tmpCList = new ArrayList<>();
        int N = Integer.parseInt(br.readLine()); // 잘라야할 점선의 개수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            if(dir == 0) { // 가로 점선 - 열간 거리
                tmpRList.add(idx);
            }

            if(dir == 1) { // 세로 점선 - 행간 거리
                tmpCList.add(idx);
            }
        }

        Collections.sort(tmpRList);
        Collections.sort(tmpCList);
        int[] r = new int[tmpRList.size() + 1];
        int[] c = new int[tmpCList.size() + 1];
        for (int i = 0; i < tmpRList.size(); i++) {
            if (i == 0) {
                r[i] = tmpRList.get(i);
            } else {
                r[i] = tmpRList.get(i) - tmpRList.get(i-1);
            }
        }

        for (int i = 0; i < tmpCList.size(); i++) {
            if (i == 0) {
                c[i] = tmpCList.get(i);
            } else {
                c[i] = tmpCList.get(i) - tmpCList.get(i-1);
            }
        }

        if(r.length == 1) {
            r[0] = H;
        } else {
            r[tmpRList.size()] = H - tmpRList.get(tmpRList.size() - 1);
        }

        if(c.length ==1) {
            c[0] = W;
        } else {
            c[tmpCList.size()] = W - tmpCList.get(tmpCList.size() - 1);
        }

        int maxWidth = 0;
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < c.length; j++) {
                int tmpWidth = r[i] * c[j];
                maxWidth = Math.max(maxWidth, tmpWidth);
            }
        }

        System.out.println(maxWidth);

    }
}