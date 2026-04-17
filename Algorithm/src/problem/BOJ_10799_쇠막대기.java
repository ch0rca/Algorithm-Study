package problem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.io.FileInputStream;

// https://www.acmicpc.net/problem/10799
public class BOJ_10799_쇠막대기 {

    public static void main(String[] args) throws IOException {
    	
    	//System.setIn(new FileInputStream("src/problem/test/boj_10799.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        int result = 0;

        for (int i = 0; i < str.length(); i++) {

            char current = str.charAt(i);

            if (current == '(') {
                // 막대 시작 or 레이저 시작
                stack.push('(');

            } else {
                // 닫는 괄호 → 무조건 pop
                stack.pop();

                if (str.charAt(i - 1) == '(') {
                    // 레이저인 경우
                    result += stack.size(); // 현재 열린 막대 개수만큼 잘려진 조각이 생김

                } else {
                    // 막대 끝인 경우
                    result += 1; // 막대의 마지막 조각 1개 추가
                }
            }
        }

        System.out.println(result);
    }
}