import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static boolean[] visited;
	static ArrayList<Integer>[] A;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int start = sc.nextInt();
		A = new ArrayList[N + 1];
		// 배열의 각 인덱스에 ArrayList 할당
		for (int i = 1; i <= N; i++) {
			A[i] = new ArrayList<Integer>();
		}
		// 그래프 완성
		for (int i = 0; i < M; i++) {
			int S = sc.nextInt();
			int E = sc.nextInt();
			A[S].add(E);
			A[E].add(S);
		}
		// 오름차순 출력을 위해 리스트 정렬하기
		for (int i = 1; i <= N; i++) {
			Collections.sort(A[i]);
		}

		visited = new boolean[N + 1];

		DFS(start);
		System.out.println();
		visited = new boolean[N + 1];
		BFS(start);
		System.out.println();

	}

	private static void BFS(int Node) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(Node);
		visited[Node] = true;
		
		while(!queue.isEmpty()) {
			int now_Node = queue.poll();
			System.out.print(now_Node + " ");
			for (int i : A[now_Node]) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}

	private static void DFS(int Node) {
		System.out.print(Node + " ");
		visited[Node] = true;
		for (int i : A[Node]) {
			if (!visited[i]) {
				DFS(i);
			}
		}
	}
}