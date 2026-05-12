package problem;

import java.util.Scanner;
import java.io.FileInputStream;

public class BOJ_14503_로봇청소기 {

   
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/problem/test/boj_14503.txt"));
    	Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); 
        int M = sc.nextInt(); 

        int r = sc.nextInt(); 
        int c = sc.nextInt(); 
        int d = sc.nextInt(); // 0=북, 1=동, 2=남, 3=서

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt(); 
            }
        }

        int count = 0; // 청소한 칸의 수

        while (true) {
            // 단계 1: 현재 칸이 청소되지 않은 경우 청소
            if (map[r][c] == 0) {
                map[r][c] = 2; 
                count++;
            }

            // 단계 2: 왼쪽으로 회전하며 청소 안 된 빈 칸 탐색
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4; // 왼쪽(반시계) 회전

                int nr = r + dr[d];
                int nc = c + dc[d];

                // 회전한 방향의 앞 칸이 청소 안 된 빈 칸이면 전진
                if (map[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                    moved = true;
                    break;
                }
            }

            if (moved) {
                continue;
            }

            // 단계 3: 4방향 모두 청소 불가 -> 현재 방향 기준 후진 시도
            int backDir = (d + 2) % 4;
            int br = r + dr[backDir];
            int bc = c + dc[backDir];

            if (map[br][bc] == 1) {
                break;
            }

            r = br;
            c = bc;
        }

        System.out.println(count);
        sc.close();
    }
}