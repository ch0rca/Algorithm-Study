package problem;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class SWEA_5215_햄버거다이어트 {
    static int N, L, maxTaste;
    static int[] taste, calorie;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/problem/test/swea_5215.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            // 재료 수(N), 제한 칼로리(L) 입력
            N = sc.nextInt();
            L = sc.nextInt();

            taste   = new int[N];
            calorie = new int[N];
            maxTaste = 0;

            for (int i = 0; i < N; i++) {
                taste[i]   = sc.nextInt();
                calorie[i] = sc.nextInt();
            }

            // depth=0, 맛 합=0, 칼로리 합=0 으로 시작
            subset(0, 0, 0);

            System.out.println("#" + test_case + " " + maxTaste);
        }
    }

    // depth  : 현재 고려 중인 재료 번호
    // sum    : 현재까지 선택한 재료의 맛 점수 합
    // ksum   : 현재까지 선택한 재료의 칼로리 합
    static void subset(int depth, int sum, int ksum) {
        // 기저 조건: 모든 재료를 다 고려했을 때
        if (depth == N) {
            // 칼로리 제한 이하일 때만 최대 맛 점수 갱신
            if (ksum <= L) {
                maxTaste = Math.max(maxTaste, sum);
            }
            return;
        }

        // 현재 재료(depth)를 선택하는 경우
        subset(depth + 1, sum + taste[depth], ksum + calorie[depth]);

        // 현재 재료(depth)를 선택하지 않는 경우
        subset(depth + 1, sum, ksum);
    }
}


// DP 버전 ----------------------------------------------------------------

