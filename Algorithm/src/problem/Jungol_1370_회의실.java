package problem;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

//https://jungol.co.kr/problem/1370
public class Jungol_1370_회의실 {
    public static void main(String[] args) throws IOException {
    	
    	System.setIn(new FileInputStream("src/problem/test/jungol_1370.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        
        int[][] meetings = new int[n][3]; // [번호, 시작, 종료]
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken()); // 번호
            meetings[i][1] = Integer.parseInt(st.nextToken()); // 시작
            meetings[i][2] = Integer.parseInt(st.nextToken()); // 종료
        }
        
        // 종료 시간 기준 오름차순 정렬, 같으면 시작 시간 기준 오름차순
        Arrays.sort(meetings, (a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];
            return a[1] - b[1];
        });
        
        List<Integer> result = new ArrayList<>();
        int lastEnd = 0;
        
        for (int i = 0; i < n; i++) {
            int start = meetings[i][1];
            int end   = meetings[i][2];
            int num   = meetings[i][0];
            
            // 종료시간 == 시작시간은 겹치지 않음
            if (start >= lastEnd) {
                result.add(num);
                lastEnd = end;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        
        // 번호를 시간대순(선택된 순서)으로 출력
        for (int num : result) {
            sb.append(num).append(" ");
        }
        
        System.out.println(sb.toString().trim());
    }
}