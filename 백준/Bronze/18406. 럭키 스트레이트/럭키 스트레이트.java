import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 시작 시간: 1:08
 * 종료 시간: 1:20
 * 문제 설명
 * 럭키스트레이트 기술 - 게임 점수가 특정 조건을 만족할 때 사용 가능
 * 캐릭터 점수 N
 * 자릿수를 기준으로 반으로 나누어 왼쪽 부분의 각 자릿수 합과 오른쪽 부분의 각 자릿수 합이 동일하면 가능
 *
 * 입력은 항상 짝수 숫자
 *
 * 입력
 * 점수 N
 *
 * 생각나는 풀이
 * N을 배열에 담은 후
 * 반반씩 더하자
 * 투 포인터를 사용해보자
 *
 *
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int[] arr = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        // 문자열 값을 정수형 ArrayList로 간단히 만드는 방법
        int N = Integer.parseInt(br.readLine());
        List<Integer> arr = new ArrayList<>();
        while(N > 0) {
            arr.add(N % 10);
            N /= 10;
        }

        int start = 0;
        int end = arr.size() - 1;

        int sumL = 0;
        int sumR = 0;
        // 투포인터 - start의 인덱스가 end의 인덱스ㄴ보다 작을 때까지
        while(start < end) {
            sumL += arr.get(start);
            sumR += arr.get(end);
            start++;
            end--;
        }
        if(sumL == sumR) {
            System.out.println("LUCKY");
        } else {
            System.out.println("READY");
        }
    }
}