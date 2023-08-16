import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 생각한 풀이
 * 1. 5*5 빙고판에 숫자를 입력받는다. - arr 배열 -  ok
 * 2. 숫자가 불렸는지 확인할 5*5 visited 배열을 만들어놓는다. - ok
 * 3. 5*5 배열에 사회자가 부른 숫자들을 하나씩 입력받는다.
 * 4. 입력받는 숫자를 빙고판의 인덱스에서 찾는다.
 * 5. 찾았다면 바로 visited배열의 그 인덱스를 true 로 바꿔준다.
 * 6. visited 배열을 for 문을 돌려서 가로줄, 세로줄, 대각선줄이 생겼는지 확인해준다.
 * 7. 만약 줄이 한줄이라도 생겼다면 cnt 의 숫자를 업데이트한다.
 * 8. 이때 += 로 증가시켜주면 안된다. 계속 visited 배열을 탐색하면서 줄 수를 찾기 때문에 탐색이 끝났을 때 cnt가 3이 아니라면 초기화되어야 한다. (그냥 함수에 int cnt = 0; 이라고 해놓으면 될듯하다.
 * 9. 만약 cnt가 3이 되면  return 한다.
 * 10. 이부분이 조금 헷갈리는데  cnt가 3이 되었을 때에 사회자가 몇번째 부른 수인지 알아야 한다.
 * 11. 이부분은 사회자가 부른 배열 input 에서 i,j 는 (0,0)부터 시작하기 때문에 i * 5 + (j+1) 로 구할 수 있다.
 * 12. 이 값을 변수에 저장해주고, 출력한다.
 */
public class Main {

    static int[][] arr;
    static boolean[][] visited;
    static int callCnt = 0;

    // 행 이동 열 탐색 델타
    static int[] dr = {1};

    // 열 이동 행 탐색 델타
    static int[] dc2 = {1};

    // 우하향 대각선 탐색 델타
    static int[] drRD = {1};
    static int[] dcRD = {1};

    // 우상향 대각선 탐색 델타
    static int[] drRT = {-1};
    static int[] dcRT = {1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 빙고판 만들기
        arr = new int[5][5];
        // 사회자가 부른 수를 지우기 위한 visited 배열
        visited = new boolean[5][5];

        // 5 * 5 빙고판 입력
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 사회자가 부른 숫자
        // 5 * 5 사회자 배열
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                // 사회자가 부른 숫자
                int num = Integer.parseInt(st.nextToken());
                // 이 숫자를 빙고판 배열에서 찾자 - 메서드 이용
                findNum(num, i, j);
            }
        }

        // 출력
        System.out.println(callCnt);
    }

    // a, b 는 사회자가 부른 숫자의 인덱스 => 3빙고가 완성될 시 계산해줄 것
    private static void findNum(int num, int a, int b) {
        // 빙고판을 for 문돌려서 찾는다
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // arr을 매개변수로 받긴 번거로우니 static으로 선언하자
                // 빙고판에서 사회자가 부른 숫자를 찾았다면
                if(arr[i][j] == num) {
                    //boolean 배열에 true로 방문 체크해야 한다
                    //boolean 배열의 i,j 인덱스는 arr의 i,j 인덱스와 같음
                    visited[i][j] = true;
                    // 이제 3 빙고가 되었나 확인해보자 -> 값을 이용해야 하니 boolean으로 메서드를 만들자
                    if(checkThreeBingo()) {
                        // 3빙고가 되었다면 사회자가 부른 배열의 번호를 알아야하기 때문에 다시 사회자가 부른 배열로 돌아가야 한다.
                        // 아니면 인덱스를 매개변수로 받아와서 지금처럼 여기서 바로 처리해도 된다.
                        // 이게 더 편하겠다.
                        // 사회자가 몇번째 불렀는지 계산 - 변수는 static의 cnt 변수
                        // static 으로 안하는 방법도 생각해보기
                        callCnt = a * 5 + (b+1);
                        System.out.println(callCnt);
                        System.exit(0);
                    }
                }
            }
        }
    }

    private static boolean checkThreeBingo() {
        // 여기서부터 좀 빡세다.
        // 3빙고가 나왔는지 찾아야한다.
        // visited 배열을 for문으로 돌면서 빙고를 찾아보자.
        // 가로, 세로, 대각선으로 각각 세번 돌려보자.

        // 빙고가 나왔을 때 추가해줄 bingoCnt 변수 선언
        int bingoCnt = 0;

        // 열 탐색 행이동
        // 행 한칸씩 오른쪽으로 이동하면서 열 0~4 탐색 : true를 찾자
        for (int i = 0; i < 5; i++) {
            int tmpCnt = 0;
            // 여러칸 쭉 이동할 때 항상 헷갈리는 부분 - while 조건을 어떻게 해야할까
            for (int r = 0; r < 5; r++) {
                // 점점 아래로 내려가며 탐색할 newR
                int newR = r * dr[0];
                // 하나라도 false 가 나오면 빙고가 안된 것 => 이번 행은 넘어감
                if(visited[newR][i] == false) {
                    continue;
                } else {
                    tmpCnt++;
                }
                    // 모두 true 라면 빙고 카운트 하나 증가
                   if(tmpCnt == 5) bingoCnt++;
            }
            }

        // 행 탐색 열이동
        // 열 한칸씩 아래로 이동하면서 행 0~4 탐색
        for (int i = 0; i < 5; i++) {
            int tmpCnt = 0;
            // 여러칸 쭉 이동할 때 항상 헷갈리는 부분 - while 조건을 어떻게 해야할까
            for (int c = 0; c < 5; c++) {
                // 점점 아래로 내려가며 탐색할 newR
                int newC = c * dc2[0];
                // 하나라도 false 가 나오면 빙고가 안된 것 => 이번 행은 넘어감
                if(visited[i][newC] == false) {
                    continue;
                } else {
                    tmpCnt++;
                }
                // 모두 true 라면 빙고 카운트 하나 증가
                if(tmpCnt == 5) bingoCnt++;
            }
        }

        // 우하 대각선 탐색 (0,0에서만 탐색해보면 됨)
        int tmpCnt = 0;
        for (int i = 0; i < 5; i++) {
            int newR = i * drRD[0];
            int newC = i * dcRD[0];

            // 하나라도 false 가 나오면 빙고가 안된 것 => 이번 행은 넘어감
            if(visited[newR][newC] == false) {
                continue;
            } else {
                tmpCnt++;
            }
            if(tmpCnt == 5) bingoCnt++;
        }

        // 우상 대각선 탐색 (4, 0)에서만 탐색해보면 됨)
        tmpCnt = 0;
        for (int i = 0; i < 5; i++) {
            int newR = 4 + i * drRT[0];
            int newC = i * dcRT[0];

            // 하나라도 false 가 나오면 빙고가 안된 것 => 이번 행은 넘어감
            if(visited[newR][newC] == false) {
                continue;
            } else {
                tmpCnt++;
            }
            if(tmpCnt == 5) bingoCnt++;
        }

        // 총 빙고 개수가 3개라면
        if (bingoCnt > 2) {
            return true;
        }


        // 총 빙고 수가 3개가 아니라면
        return false;
    }


}