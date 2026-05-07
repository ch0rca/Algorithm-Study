package problem;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.InputStreamReader;

public class BOJ_1062_가르침 {

    static int N, K;
    static int[] wordMasks;   
    static int[] candidates; // 필수 5개를 제외한 나머지 알파벳 인덱스
    static int candidateSize;
    static int answer = 0;

    static final int BASE_MASK = (1 << ('a' - 'a')) | (1 << ('n' - 'a'))
                               | (1 << ('t' - 'a')) | (1 << ('i' - 'a'))
                               | (1 << ('c' - 'a'));

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/problem/test/boj_1062.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        wordMasks = new int[N];

        for (int i = 0; i < N; i++) {
            String word = sc.next();
            int mask = 0;
            for (char ch : word.toCharArray()) {
                mask |= (1 << (ch - 'a')); 
            }
            wordMasks[i] = mask;
        }

        // K < 5: 필수 알파벳조차 가르칠 수 없음
        if (K < 5) {
            System.out.println(0);
            return;
        }

        // K >= 26: 모든 알파벳을 가르칠 수 있음
        if (K >= 26) {
            System.out.println(N);
            return;
        }

        // 필수 5개를 제외한 나머지 21개 알파벳 추출
        List<Integer> candList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if ((BASE_MASK & (1 << i)) == 0) {
                candList.add(i);
            }
        }
        candidateSize = candList.size();
        candidates = new int[candidateSize];
        for (int i = 0; i < candidateSize; i++) {
            candidates[i] = candList.get(i);
        }

        // DFS로 K-5개의 알파벳 선택
        dfs(0, 0, BASE_MASK);

        System.out.println(answer);
    }

    // start: 탐색 시작 인덱스, count: 현재까지 선택한 추가 알파벳 수, taught: 현재 가르친 알파벳 비트마스크
    static void dfs(int start, int count, int taught) {
        if (count == K - 5) {
            int cnt = 0;
            for (int mask : wordMasks) {
                if ((mask & taught) == mask) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
            return;
        }

        if (candidateSize - start < (K - 5) - count) {
            return;
        }

        for (int i = start; i < candidateSize; i++) {
            dfs(i + 1, count + 1, taught | (1 << candidates[i]));
        }
    }
}