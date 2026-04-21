package problem;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;


public class SWEA_1247_최적경로 {

    static int N;
    static int startX, startY;   // 회사 좌표 (출발점)
    static int homeX, homeY;     // 집 좌표 (도착점)
    static int[] custX, custY;   // 고객들의 x, y 좌표
    static int[] order;          // 백트래킹으로 결정되는 방문 순서
    static boolean[] visited;    // 고객 방문 여부
    static int minDist;          // 현재까지 구한 최단 거리

    public static void main(String[] args) throws IOException {
    	
    	System.setIn(new FileInputStream("src/problem/test/swea_1247.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt(); // 총 테스트케이스 수

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt(); // 고객 수 

            // 회사 좌표 (출발점)
            startX = sc.nextInt();
            startY = sc.nextInt();

            // 집 좌표 (도착점)
            homeX = sc.nextInt();
            homeY = sc.nextInt();

            // N명의 고객 좌표
            custX = new int[N];
            custY = new int[N];
            for (int i = 0; i < N; i++) {
                custX[i] = sc.nextInt();
                custY[i] = sc.nextInt();
            }

            // 순열 탐색 준비
            order   = new int[N];
            visited = new boolean[N];
            minDist = Integer.MAX_VALUE;

            // 백트래킹으로 모든 방문 순서(순열) 탐색
            permute(0, startX, startY, 0);

            System.out.println("#" + tc + " " + minDist);
        }
        sc.close();
    }

    /*
     * @param depth   현재까지 선택한 고객 수
     * @param curX    현재 위치 x
     * @param curY    현재 위치 y
     * @param accDist 현재까지 누적 이동 거리
     */
    static void permute(int depth, int curX, int curY, int accDist) {

        // 가지치기: 이미 minDist 이상이면 탐색 중단 
        if (accDist >= minDist) return;

        if (depth == N) {
            // 모든 고객 방문 완료 → 집까지 거리 추가
            int total = accDist + manhattan(curX, curY, homeX, homeY);
            if (total < minDist) {
                minDist = total;
            }
            return;
        }

        // 아직 방문하지 않은 고객을 순서대로 선택
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i;

                // 현재 위치 → 고객 i 까지의 거리를 누적하여 재귀 호출
                int moveDist = manhattan(curX, curY, custX[i], custY[i]);
                permute(depth + 1, custX[i], custY[i], accDist + moveDist);

                visited[i] = false; // 백트래킹: 선택 취소
            }
        }
    }

    //맨해튼 거리 계산: |x1-x2| + |y1-y2|
    static int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}