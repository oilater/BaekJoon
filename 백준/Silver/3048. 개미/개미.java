import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

/**
 * 문제 설명
 * 서로 반대방향으로 이동하던 두 개미 그룹
 * 좁은 길에서 만나면 점프해서 넘어감
 * 그러나 자신의 앞에 반대 방향으로 움직이던 개미가 있는 경우에만 점프
 *
 * ABC DEF
 * CBA DEF
 * CB -><-DA-><-EF
 * CDBEAF
 *
 * 생각나는 풀이
 * 각각의 알파벳마다 방향 정보를 가지고 있어야 한다.
 *
 * 배열이나 클래스로 풀어볼까?
 *
 * 어려운 점
 * 배열을 합쳐야 하나 생각하기도 어렵고
 * 배열의 순서를 바꾸기가 까다롭다 swap 함수를 써야 하는건가
 */
public class Main {

    // 개미의 이름과 방향을 저장한 클래스
    static class Node {
        String name;
        String dir;

        public Node(String name, String dir) {
            this.name = name;
            this.dir = dir;
        }
    }

    static Node[] antArr1;
    static Node[] antArr2;
    static Node[] antArr;
    static int N1, N2, T;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 입력
        N1 = Integer.parseInt(st.nextToken()); // 왼쪽 개미 그룹
        N2 = Integer.parseInt(st.nextToken()); // 오른쪽 개미 그룹
        StringBuilder sb = new StringBuilder(br.readLine());
        String[] ant1 = sb.reverse().toString().split("");
        String[] ant2 = br.readLine().split("");
       ; // 개미 그룹 1 배열은 뒤집어주기

        T = Integer.parseInt(br.readLine()); // T초

        antArr1 = new Node[N1];
        antArr2 = new Node[N2];

        for (int i = 0; i < N1; i++) {
            antArr1[i] = new Node(ant1[i], "L");
        }

        for (int i = 0; i < N2; i++) {
            antArr2[i] = new Node(ant2[i], "R");
        }

        // 두 개미 노드 배열을 하나의 리스트로 합쳐주기 => CBADEF 로 잘 나옴
        antArr = new Node[N1+N2];
        int idx = 0;
        for (int i = 0; i < N1; i++) {
            antArr[idx++] = antArr1[i];
        }
        for (int i = 0; i < N2; i++) {
            antArr[idx++] = antArr2[i];
        }

        // swap 작업 시작
        // 1초당 swap 하기 때문에 시간만큼 돌려주자
        for (int time = 0; time < T; time++) {
            for (int i = 0; i < N1+N2 - 1; i++) {
                if(antArr[i].dir.equals("L") && antArr[i+1].dir.equals("R")) {
                    swap(i, i+1);
                    i++;
                }
            }
        }
        for (Node ant: antArr) {
            System.out.print(ant.name);
        }
    }

    // swap
    private static void swap(int a, int b) {
        Node temp = antArr[a];
        antArr[a] = antArr[b];
        antArr[b] = temp;
    }


}