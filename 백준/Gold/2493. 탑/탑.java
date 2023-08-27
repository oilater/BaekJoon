import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 탑 클래스를 만든다.
 * 답 출력할 리스트를 만든다.
 * 인덱스, 높이 저장
 * 탑 리스트를 만들어 각 탑들 저장
 * 스택을 돌며 아무것도 없거나 자기보다 높은게 없다면 0
 * 일단 스택에 추가
 * 높은 것이 있다면 그 탑의 인덱스 저장
 * 일단 스택에 추가
 *
 *
 *
 * 그리고 스택에 추가
 * 높은 탑이 있다면 그 탑의 인덱스 저장, 스택에 추가
 *
 */
public class Main {
    static class Tower {
        int idx;
        int height;
        public Tower(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    static int N;
    static Tower[] towers;
    static int[] result;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        towers = new Tower[N];
        result = new int[N]; // 답 출력 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken()); // 높이
            towers[i] = new Tower(i+1, height); // 타워 배열에  인덱스, 높이 저장
        }

        Stack<Tower> stack = new Stack<>();
        for (int i = 0; i < N; i++) {

            if (stack.isEmpty()) { // 스택이 비어있다면
                stack.push(towers[i]); // 스택에 지산을 넣고
                result[i] = 0; // 결과에는 0 넣음
            } else { // 스택에 탑이 있다면
                while(true) { // 자신보다 낮은 탑들 모두 제거
                    if(stack.peek().height < towers[i].height) {
                        stack.pop();
                        if(stack.isEmpty()) { // 스택에 아무것도 안남았다면
                            result[i] = 0; // 결과에 0 넣기
                            stack.push(towers[i]); // 그리고 스택에 자신 넣기
                            break;
                        }
                    } else { // 스택 peek가 자기보다 높은 탑이라면
                        result[i] = stack.peek().idx; // peek의 idx 결과에 넣기
                        stack.push(towers[i]); // 자신도 스택에 추가
                        break;
                    }
                }
            }
        }
        // 결과 출력
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(' ');
        }
        System.out.println(sb);
    }
}