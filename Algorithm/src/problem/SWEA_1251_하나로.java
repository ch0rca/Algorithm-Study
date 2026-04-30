package problem;

import java.util.Scanner;
import java.io.FileInputStream;

public class SWEA_1251_하나로 {
    public static void main(String args[]) throws Exception {
    	
    	System.setIn(new FileInputStream("src/problem/test/swea_1251.txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            // 섬의 개수 N 입력
            int N = sc.nextInt();

            long[] x = new long[N];
            long[] y = new long[N];

            // X 좌표 입력
            for (int i = 0; i < N; i++) {
                x[i] = sc.nextLong();
            }

            // Y 좌표 입력
            for (int i = 0; i < N; i++) {
                y[i] = sc.nextLong();
            }

            // 환경 부담 세율 E 입력
            double E = sc.nextDouble();

            // 섬이 1개이면 터널 불필요, 비용 0
            if (N == 1) {
                System.out.println("#" + test_case + " 0");
                continue;
            }

            // minCost[v]: MST에서 v번 섬을 연결할 때 드는 최소 L^2
            // inMST[v]: v번 섬이 MST에 포함되었는지 여부
            long INF = Long.MAX_VALUE;
            long[] minCost = new long[N];
            boolean[] inMST = new boolean[N];

            for (int i = 0; i < N; i++) {
                minCost[i] = INF;
            }
            minCost[0] = 0; // 0번 섬부터 시작

            long total = 0; // MST 전체 L^2 합

            for (int iter = 0; iter < N; iter++) {

                // MST 미포함 노드 중 minCost 최솟값인 노드 u 선택
                int u = -1;
                for (int v = 0; v < N; v++) {
                    if (!inMST[v]) {
                        if (u == -1 || minCost[v] < minCost[u]) {
                            u = v;
                        }
                    }
                }

                // u를 MST에 포함하고 비용 누적
                inMST[u] = true;
                total += minCost[u];

                // u 기준으로 미포함 노드들의 minCost 갱신
                for (int v = 0; v < N; v++) {
                    if (!inMST[v]) {
                        long dx = x[u] - x[v];
                        long dy = y[u] - y[v];
                        long dist2 = dx * dx + dy * dy;
                        if (dist2 < minCost[v]) {
                            minCost[v] = dist2;
                        }
                    }
                }
            }

            // 최종 환경 부담금
            long answer = Math.round(E * (double) total);
            System.out.println("#" + test_case + " " + answer);

        }
    }
}