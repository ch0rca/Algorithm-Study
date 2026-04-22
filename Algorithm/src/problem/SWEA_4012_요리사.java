package problem;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class SWEA_4012_요리사 {
    static int N;
    static int[][] S;
    static boolean[] inA;  // inA[i] = true이면 i번 식재료는 A그룹
    static int answer;

    public static void main(String args[]) throws Exception {
        
    	System.setIn(new FileInputStream("src/problem/test/swea_4012.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            S = new int[N][N];
            inA = new boolean[N];

            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    S[i][j] = sc.nextInt();

            answer = Integer.MAX_VALUE;

            // 0번 식재료를 A그룹에 고정하고 나머지를 재귀로 배정
            inA[0] = true;
            dfs(1, 1);  // 1번 식재료부터 탐색, A그룹 현재 인원 1명

            System.out.println("#" + test_case + " " + answer);
        }
    }


    static void dfs(int index, int countA) {
        if (index == N) {
            if (countA == N / 2) {
                int diff = Math.abs(calcTaste(true) - calcTaste(false));
                answer = Math.min(answer, diff);
            }
            return;
        }

        int remaining = N - index;         // 아직 배정 안 된 식재료 수
        int needA = N / 2 - countA;       // A그룹에 더 필요한 인원

        // A그룹에 넣기: 아직 A그룹 자리가 남아있을 때만
        if (needA > 0 && needA <= remaining) {
            inA[index] = true;
            dfs(index + 1, countA + 1);
        }

        // B그룹에 넣기: B그룹에 자리가 남아있을 때만
        int countB = index - countA;
        int needB = N / 2 - countB;
        if (needB > 0 && needB <= remaining) {
            inA[index] = false;
            dfs(index + 1, countA);
        }
    }

    static int calcTaste(boolean isA) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (inA[i] != isA) continue;
            for (int j = 0; j < N; j++) {
                if (inA[j] != isA) continue;
                if (i != j) sum += S[i][j];
            }
        }
        return sum;
    }
}