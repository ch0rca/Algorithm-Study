package problem;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1759
public class BOJ_1759_암호만들기 {

    static int L, C;
    static char[] arr;      // 입력받은 C개의 알파벳
    static char[] result;   // 선택된 L개의 문자를 담는 배열
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
    	
    	System.setIn(new FileInputStream("src/problem/test/boj_1759.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken()); // 암호 길이
        C = Integer.parseInt(st.nextToken()); // 사용할 알파벳 수

        arr = new char[C];
        result = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        // 오름차순 정렬 → 조합 생성 시 사전순 자동 보장
        Arrays.sort(arr);

        // depth=0, start=0 으로 조합 탐색 시작
        combi(0, 0);

        System.out.print(sb);
    }

    /* 백트래킹으로 L개의 조합을 생성
     * @param depth 현재까지 선택한 문자 수
     * @param start 이번 선택에서 탐색을 시작할 arr 인덱스
     */
    static void combi(int depth, int start) {

        // L개를 모두 선택 완료
        if (depth == L) {
            if (isValid()) {  // 모음/자음 조건 검사
                sb.append(new String(result)).append('\n');
            }
            return;
        }

        // 남은 문자 수 < 앞으로 선택해야 할 수 → 불가능한 경우
        if (C - start < L - depth) return;

        // start ~ C-1 범위에서 하나씩 선택
        for (int i = start; i < C; i++) {
            result[depth] = arr[i];      // i번째 문자를 현재 depth 자리에 배치
            combi(depth + 1, i + 1);     // 다음 자리는 i+1부터 (오름차순 유지)
        }
    }


    // result[]가 모음 >= 1, 자음 >= 2 조건을 만족하는지 검사

    static boolean isValid() {
        int vowel = 0;
        int consonant = 0;

        for (int i = 0; i < L; i++) {
            if (check(result[i])) vowel++;    // 모음이면 vowel 증가
            else consonant++;                 // 자음이면 consonant 증가
        }

        return vowel >= 1 && consonant >= 2; 
    }


     //문자 a가 모음(a, e, i, o, u)이면 true, 자음이면 false
    static boolean check(char a) {
        return a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u';
    }
}
