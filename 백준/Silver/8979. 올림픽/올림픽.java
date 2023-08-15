import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간: 5:54
 * 종료 시간:
 * 문제 설명
 * 올림픽 순위 매기는 법
 * 1. 금메달 수가 많은 나라
 * 2. 금메달 수 같다면 은메달 더 많은 나라
 * 3. 금, 은메달 수 모두 같으면 동메달 수가 더 많은 나라
 *
 * 각 국가는 1~N으로 표현
 * 국가의 등수 : 자신보다 더 잘한 나라 수 + 1
 * 금은동 메달 수 모두 같으면 공동 순위
 * 1, 2, 4 이런식 가능
 * 금,은, 동 메달 정보 받아서 어느 국가가 몇등 했는지 알려주는 프로그램 만들자
 *
 * 입력
 * 첫줄: 국가의 수 N, 등수를 알고 싶은 국가 K (1<=K<=N)
 * 각 N개의 줄: 국가를 나타내는 정수, 금, 은, 동메달 수
 *
 * 조건
 * 전체 메달 수의 총합은 1,000,000 이하
 *
 * 생각나는 풀이
 * 정렬 문제다
 * 국가 클래스를 만들어서 금, 은, 동 정보를 저장하자
 * 각 국가 객체의 금~동 까지 메달을 비교하면서 원하는 국가의 순위를 찾자
 * priorityQueue로 풀 수도 있을 것 같다.
 * 아니면 배열에 일단 넣어놓은 후에 컴퍼레이터로 비교해서 순위를 정렬하자
 */
public class Main {
    static class Nation{
        int nationNum;
        int gold, silver, bronze;
        int rank = 1;

        public Nation(int nationNum, int gold, int silver, int bronze) {
            this.nationNum = nationNum;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }
    }

    int rank = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 국가의 수
        int K = Integer.parseInt(st.nextToken()); // 등수를 알고 싶은 국가

        List<Nation> nationList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nationNum = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            Nation nation = new Nation(nationNum, gold, silver, bronze);
            nationList.add(nation);
        }

        // 나라 list 정렬
        Collections.sort(nationList, ((o1, o2) ->{
            if(o1.gold == o2.gold) {
                if(o1.silver == o2.silver) {
                    return o2.bronze - o1.bronze;
                }
                return o2.silver - o1.silver;
            }
            return o2.gold- o1.gold;
        } ));

        // 랭크 정리
        int G, S, B = 0;
        int tmpRank = 0;
        for (int i = 1; i < nationList.size(); i++) {
            Nation start = nationList.get(0);
            Nation tmp = nationList.get(i);
            G = tmp.gold;
            S = tmp.silver;
            B = tmp.bronze;
            if(G == start.gold && S == start.silver && B == start.bronze) {
                tmpRank++;
                tmp.rank = nationList.get(i-1).rank;
            } else {
                tmp.rank += 1 + tmpRank;
                tmpRank = 0;
            }
        }

        for (int i = 0; i < nationList.size(); i++) {
            if(nationList.get(i).nationNum == K) {
                System.out.println(nationList.get(i).rank);
            }
        }


    }
}