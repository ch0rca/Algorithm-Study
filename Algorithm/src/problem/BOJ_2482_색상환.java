package problem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2482_색상환 {

    public static void main(String[] args) throws Exception {
    	
    	System.setIn(new FileInputStream("src/problem/test/boj_2482.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int K = Integer.parseInt(br.readLine().trim());

        final long MOD = 1_000_000_003L;

        // 인접하지 않게 K개를 고르려면 최소 2K개가 필요
        if (2 * K > N) {
            System.out.println(0);
            return;
        }

        // K == 1이면 N개 중 아무거나 1개 선택
        if (K == 1) {
            System.out.println(N % MOD);
            return;
        }

        // dp[i][j] = 1번~i번 색(선형)에서 j개를 인접하지 않게 고르는 경우의 수
        long[][] dp = new long[N + 1][K + 1];

        // 초기값 설정
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[2][0] = 1;
        dp[2][1] = 2; // 1번 선택, 2번 선택

        for (int i = 3; i <= N; i++) {
            dp[i][0] = 1; // 아무것도 선택 안 하는 경우는 항상 1가지
            for (int j = 1; j <= K; j++) {
                // i번 미선택: dp[i-1][j]
                // i번 선택 (i-1번 강제 제외): dp[i-2][j-1]
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }

        // Case 1: 1번 색을 선택하는 경우
        long case1 = dp[N - 2][K - 1];

        // Case 2: 1번 색을 선택하지 않는 경우
        long case2 = dp[N - 1][K];

        System.out.println((case1 + case2) % MOD);
    }
}