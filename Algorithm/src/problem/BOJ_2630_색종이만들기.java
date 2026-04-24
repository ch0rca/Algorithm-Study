package problem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

//https://www.acmicpc.net/problem/2630
public class BOJ_2630_색종이만들기 {

    static int[][] paper;
    static int whiteCount = 0;
    static int blueCount = 0;

    public static void main(String[] args) throws IOException {
    	
    	System.setIn(new FileInputStream("src/problem/test/boj_2630.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        paper = new int[N][N];

        // 종이 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                paper[i][j] = sc.nextInt();
            }
        }

        check(0, 0, N);

        System.out.println(whiteCount);
        System.out.println(blueCount);
    }

    // (row, col)을 시작점으로 size x size 영역을 검사하는 재귀 함수
    static void check(int row, int col, int size) {
        // 현재 영역의 기준 색 (좌상단 칸 기준)
        int color = paper[row][col];

        // 현재 영역 전체가 같은 색인지 확인
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (paper[i][j] != color) {
                    // 다른 색이 존재하면 4등분하여 재귀 분할
                    int half = size / 2;
                    check(row,          col,          half); // 좌상
                    check(row,          col + half,   half); // 우상
                    check(row + half,   col,          half); // 좌하
                    check(row + half,   col + half,   half); // 우하
                    return;
                }
            }
        }

        // 전체가 같은 색이면 카운트 증가
        if (color == 0) {
            whiteCount++;
        } else {
            blueCount++;
        }
    }
}