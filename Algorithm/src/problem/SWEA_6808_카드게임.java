package problem;

import java.util.Scanner;
import java.io.FileInputStream;

public class SWEA_6808_카드게임 {
	
    static int[] gyuYoung = new int[9];  
    static int[] inYoung = new int[9];  
    static boolean[] used = new boolean[9]; // 인영이 카드 사용 여부
    static long winCount = 0;  // 규영이가 이기는 경우
    static long loseCount = 0; // 규영이가 지는 경우

    public static void main(String args[]) throws Exception {
        
    	System.setIn(new FileInputStream("src/problem/test/swea_6808.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            // 규영이 카드 입력
            boolean[] hasCard = new boolean[19]; // 1~18 중 규영이가 가진 카드 체크
            for (int i = 0; i < 9; i++) {
                gyuYoung[i] = sc.nextInt();
                hasCard[gyuYoung[i]] = true;
            }

            // 인영이 카드 = 규영이가 갖지 않은 나머지 카드
            int idx = 0;
            for (int i = 1; i <= 18; i++) {
                if (!hasCard[i]) {
                    inYoung[idx++] = i;
                }
            }

            // 경우의 수 초기화
            winCount = 0;
            loseCount = 0;
            used = new boolean[9];

            // 인영이 카드 순열을 모두 탐색 (9! 가지)
            permute(new int[9], 0);

            System.out.println("#" + test_case + " " + winCount + " " + loseCount);
        }
    }

    // 인영이의 카드 순서를 순열로 생성하고, 완성되면 점수 계산
    static void permute(int[] order, int depth) {
        if (depth == 9) {
            compare(order);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!used[i]) {
                used[i] = true;
                order[depth] = inYoung[i]; // i번째 카드를 depth 라운드에 배치
                permute(order, depth + 1);
                used[i] = false; 
            }
        }
    }

    // 규영이와 인영이의 카드를 라운드별로 비교하여 승패 판정
    static void compare(int[] order) {
        int gyuScore = 0;  
        int inScore = 0;   

        for (int i = 0; i < 9; i++) {
            int g = gyuYoung[i]; 
            int in = order[i];   

            if (g > in) {
                gyuScore += g + in; 
            } else {
                inScore += g + in;  
            }
        }

        if (gyuScore > inScore) {
            winCount++;
        } else if (gyuScore < inScore) {
            loseCount++;
        }
    }
}