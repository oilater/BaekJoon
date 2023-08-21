import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 업무 너무 많아 분단위로 업무 추가되고 있기 때문
 *
 * 업무 처리 규칙
 * 1. 가장 최근에 주어진 순서대로 , 업무를 받으면 바로 시작 - 스택?
 * 2. 업무를 하던 도중 새로운 업무가 추가 된다면 하던 업무 중단 하고 새로운 업무 진행
 * 새 업무 끝났다면 이전 업무를 이전에 하던 부분부터 이어서 한다.
 *
 * => 스택 자료구조를 나타냄
 *
 * 삼성이의 업무 평가를 점수로 예상해보자
 *
 * 입력 : 이번 분기가 몇분인지 나타내는 정수 N
 * N줄 동안 이번 분기가 시작하고 N분째에 주어진 업무의 정보가 아래의 두 경우 하나로 주어진다.
 * 1 A T 업무의 만점은 A점이고, 삼성이가 과제 해결하는데 걸리는 시간 T분
 * 0 업무 주어지지 않음
 *
 * 예제
 * 3
 * 1 100 3
 *
 * 생각나는 풀이
 */
public class Main {
    // 업무의 정보를 기록할 Node 클래스 생성
    static class Node {
        int score = 0; // 각 업무의 평가 점수
        int time = 0; // 각 업무를 하는데 걸리는 시간

        public Node (int score, int time) {
            this.score = score;
            this.time = time;
        }
    }

    static int sum = 0; // 삼성이의 총 업무 평가
    public static void main(String[] args) throws NumberFormatException, IOException {
        Stack<Node> stack = new Stack<>(); // 업무들을 담아줄 stack
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 이번 분기가 몇분인지 나타내는 정수  N분
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tf = Integer.parseInt(st.nextToken()); // 0 혹은 1 일이 있는지 없는지
            if(tf == 1) { // tf가 1일 떄 - 새 업무가 있을 때
                int A = Integer.parseInt(st.nextToken()); // 해당 업무의 만점
                int T = Integer.parseInt(st.nextToken()); // 해당 업무 하는데 걸리는 시간
                Node node = new Node(A, T); // 새로운 업무 인스턴스 생성
                stack.add(node);

            }
            // tf가 0일 때는 새 업무가 없을 때이므로 매개변수를 더 받지 않고 넘어가기
            if(stack.isEmpty()) continue;
            Node last = stack.peek(); // stack 의 최상단 peek last에 저장
            last.time--; // i가 한번 돌때마다 stack의 최상단에 있는 last의 시간을 1씩 깎아주기
            if(last.time == 0) { // last의 시간이 0이 되면
                sum += last.score; // 총합에 last의 점수를 더해주기
                stack.pop(); // 스택에서 last 제거
            }
        }

        System.out.println(sum); // 총합 출력
    }


}