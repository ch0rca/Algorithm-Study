package problem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.io.FileInputStream;


//https://www.acmicpc.net/problem/7785
public class BOJ_7785_회사에있는사람 {

	public static void main(String[] args) throws IOException {

    	//System.setIn(new FileInputStream("src/problem/test/boj_7785.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 현재 회사에 있는 사람들을 저장할 집합
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String state = st.nextToken();

            if (state.equals("enter")) {
                // 출근 → 집합에 추가
                set.add(name);
            } else {
                // 퇴근 → 집합에서 제거
                set.remove(name);
            }
        }

        // 집합 → 리스트 변환 (정렬을 위해)
        ArrayList<String> list = new ArrayList<>(set);

        // 사전 역순 정렬
        Collections.sort(list, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();

        for (String name : list) {
            sb.append(name).append("\n");
        }

        System.out.print(sb.toString());

	}

}
