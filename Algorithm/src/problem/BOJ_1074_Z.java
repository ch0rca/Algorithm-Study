package problem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

//https://www.acmicpc.net/problem/1074
public class BOJ_1074_Z {

    public static void main(String[] args) throws IOException {
    	
    	System.setIn(new FileInputStream("src/problem/test/boj_1074.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        // 전체 배열 크기는 2^N
        int size = (int) Math.pow(2, N);

        System.out.println(solve(r, c, size, 0));
    }

    // r, c: 현재 탐색 기준 좌표
    // size: 현재 사분면의 한 변 길이
    // count: 지금까지 건너뛴 칸 수
    static long solve(int r, int c, int size, long count) {
    	
        if (size == 1) {
            return count;
        }

        int half = size / 2;
        
        long quadrantSize = (long) half * half; 

        if (r < half && c < half) {
            // 왼쪽 위 사분면: 아무것도 건너뛰지 않음
            return solve(r, c, half, count);

        } else if (r < half && c >= half) {
            // 오른쪽 위 사분면: 왼쪽 위 사분면 건너뜀
            return solve(r, c - half, half, count + quadrantSize);

        } else if (r >= half && c < half) {
            // 왼쪽 아래 사분면: 위쪽 두 사분면 건너뜀
            return solve(r - half, c, half, count + quadrantSize * 2);

        } else {
            // 오른쪽 아래 사분면: 위쪽 두 사분면 + 왼쪽 아래 사분면 건너뜀
            return solve(r - half, c - half, half, count + quadrantSize * 3);
        }
    }
}