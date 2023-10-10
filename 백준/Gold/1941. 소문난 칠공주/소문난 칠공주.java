import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제설명
 * 7명의 여학생들로 구성
 * 7명은 가로나 세로로 반드시 인접
 * 7명 중 4명 이상은 S 이다솜파여야 한다.
 * 소문난  칠공주를 결성할 수 있는 모든 경우의 수를 구해라
 * 
 * 2차원 배열에서 7명 고르기
 * 25C7 = 480000 - 시간 초과 안남
 * - 어떻게 고를 수 있을까?
 * 
 * 고른 후에 어떻게 표시할 수 있을까?
 * boolean 배열을 만들자
 * 선택했다면 true로 바꿔줌
 * 7명 다 선택완료되면 모두가 인접하는지 확인해야 한다. - DFS 로 확인해보자
 * -> visited 배열 필요 -> 조합 완성됐을 때 초기화해줘야 함
 * 격자 벗어나거나, 방문한 곳이거나, false인 곳일 때는 continue
 * 아니라면 이동 후 cnt++
 * cnt가 7이 되면 연결된 것이므로 true 반환
 * 이게 true라면 ans 값 1증가
 */
public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static char[][] map = new char[5][]; // 5 * 5 격자 생성
	static boolean[][] isSeven = new boolean[5][5]; // 선택된 여학생 7명 표시할 배열
	static boolean[][] visited = new boolean[5][5];
	static int ans;
	static int connected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		} // 맵 입력

		combination(0, 0, 0);

		System.out.println(ans);
	}

	private static void combination(int start, int cnt, int sCnt) {

		if (cnt == 7) {
			if (sCnt < 4) return; // 이다솜파가 4명 미만이라면 return;
			visited = new boolean[5][5];
			connected = 0;
			if (isConnected((start-1)/5, (start-1)%5)) ans++;

			return;
		}

		for (int i = start; i < 25; i++) {
			int r = i / 5;
			int c = i % 5;
			isSeven[r][c] = true;

			if (map[r][c] == 'S') combination(i+1, cnt+1, sCnt+1);
			else combination(i+1, cnt+1, sCnt);

			isSeven[r][c] = false;
		}
	}

	private static boolean isConnected(int r, int c) {
		if (r < 0 || r >= 5 || c < 0 || c >= 5) return false;
		if (visited[r][c]) return false;
		if (!isSeven[r][c]) return false;
		
		
		visited[r][c] = true; // 현재 점 방문 처리
		connected++;
		
		if (connected == 7) {
			return true;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (isConnected(nr, nc)) return true;
		}

		return false;
	}

}