package problem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.FileInputStream;

//https://www.acmicpc.net/problem/17070
public class BOJ_17070_파이프옮기기 {

    public static void main(String[] args) throws Exception {
    	
    	System.setIn(new FileInputStream("src/problem/test/boj_17070.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[r][c][dir]: (r,c)에 방향 dir로 파이프 끝이 놓이는 경우의 수
        // dir 0: 가로, 1: 대각선, 2: 세로
        int[][][] dp = new int[N][N][3];

        // 초기 상태
        dp[0][1][0] = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 현재 위치가 벽이거나 도달한 경우의 수가 없으면 스킵
                if (grid[r][c] == 1) continue;
                if (dp[r][c][0] == 0 && dp[r][c][1] == 0 && dp[r][c][2] == 0) continue;

                // 가로 이동: 오른쪽 (r, c+1)
                // 가로->가로, 대각->가로 전이
                if (c + 1 < N && grid[r][c + 1] == 0) {
                    dp[r][c + 1][0] += dp[r][c][0];
                    dp[r][c + 1][0] += dp[r][c][1];
                }

                // 세로 이동: 아래 (r+1, c)
                // 세로->세로, 대각->세로 전이
                if (r + 1 < N && grid[r + 1][c] == 0) {
                    dp[r + 1][c][2] += dp[r][c][2];
                    dp[r + 1][c][2] += dp[r][c][1];
                }

                // 대각선 이동: 오른쪽 아래 (r+1, c+1)
                // 가로->대각, 세로->대각, 대각->대각 전이
                if (r + 1 < N && c + 1 < N
                        && grid[r][c + 1] == 0
                        && grid[r + 1][c] == 0
                        && grid[r + 1][c + 1] == 0) {
                    dp[r + 1][c + 1][1] += dp[r][c][0];
                    dp[r + 1][c + 1][1] += dp[r][c][1];
                    dp[r + 1][c + 1][1] += dp[r][c][2];
                }
            }
        }

        int answer = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
        System.out.println(answer);
    }
}