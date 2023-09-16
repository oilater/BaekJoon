import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * 모든 컴퓨터를 연결하는 데에 필요한 최소 비용 구하기
 *
 * 입력값
 * 첫줄: 컴퓨터의 수 N
 * 둘째줄: 간선의 수 M
 * 셋째줄 ~ M개의 줄 : a컴퓨터와 b컴퓨터를 연결하는데 드는 비용 c
 * a, b 는 같을 수도 있다.
 * 문제 해결 프로세스
 * 1. 최소 스패닝 트리
 * 2. from to weight
 * weight 작은 순서대로 정렬
 */
public class Main {
    static int N, M;
    static int[] roots;
    static Edge[] edgeList;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge (int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        roots = new int[N+1]; // 대표 노드 배열
        for (int i = 1; i <= N; i++) {
            roots[i] = i;
        }
        
        edgeList = new Edge[M];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(a, b, c);
        }

        int minCost = 0;

        Arrays.sort(edgeList);
        for (Edge edge : edgeList) {
            if(union(edge.from, edge.to)) {
                minCost += edge.weight;
            }
        }
        System.out.println(minCost); // 정답 출력
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        roots[bRoot] = aRoot;
        return true;
    }

    private static int find (int n) {
        if (roots[n] == n) return n;
        return roots[n] = find(roots[n]);
    }
}