package problem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293_동전1 {
    public static void main(String[] args) throws Exception {
    	
    	System.setIn(new FileInputStream("src/problem/test/boj_2293.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 동전 종류 수
        int k = Integer.parseInt(st.nextToken()); // 목표 금액

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine().trim());
        }

     
        int[] dp = new int[k + 1];
        dp[0] = 1; 

        for (int i = 0; i < n; i++) {
            int c = coins[i];
            //  c원 이상의 금액만 갱신
            for (int j = c; j <= k; j++) {
                dp[j] += dp[j - c];
            }
        }

        System.out.println(dp[k]);
    }
}