import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 첫번째 수로 양의 정수가 주어짐
 * 두번째 수 : 양의 정수 중에서 하나 선택
 *
 * 세번째 이후부터 나오는 모든 수는 앞의 앞의 수에서 앞의수를 빼서 만든다
 * ex) 3번째 수 = 1번째 수 - 2번째 수
 * 음의 정수가 만들어지면 음의 정수를 버리고 더 이상 수를 만들지 않는다.
 *
 * 예시
 * 100 60 40 20 20 0 20
 * 100 62 38 24 14 10 4 6
 *
 * 첫번쨰 수가 입력으로 주어질 떄 이 수에서 시작하여 위의 규칙으로 만들어지는 최대 개수의 수들을
 * 구하는 프로그램을 만드시오. 최대 개수 여러개면 그중 하나만 골라서 출력
 *
 * 첫번째 수 <= 30000
 *
 * 생각나는 풀이
 * 리스트 만들자
 * 두번째 수로 뭘 받냐에 따라 결정된다.
 * for 문 돌리면서 리스트에 1번째, 2번째 수 넣고 그 뒤는 while 문으로 음수가 될때까지 반복
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        List<Integer> copy = new ArrayList<>();
        int max = 0;
        for (int i = N; i >= 1 ; i--) {
            list.add(N); // 첫번째 수 넣기
            list.add(i); // 두번째 수 넣기
            while (true) {
                int tmp = list.get(list.size()-2) - list.get(list.size()-1);
                if(tmp < 0) {
                    break;
                } else {
                    list.add(tmp);
                }
            }

            if(list.size() > max) {
                max = list.size();
                copy.clear();
                copy.addAll(list);
            }
            list.clear();
        }

        sb.append(max).append('\n');
        for (int i: copy) {
            sb.append(i).append(' ');
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}