package problem;

import java.util.Arrays;

public class PG_42884_단속카메라  {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);

        int count = 1;
        int camera = routes[0][1]; // 첫 번째 차량의 진출 지점에 카메라 설치

        for (int i = 1; i < routes.length; i++) {
            // 현재 차량의 진입 지점이 카메라 위치를 벗어난 경우
            if (routes[i][0] > camera) {
                count++;
                camera = routes[i][1]; 
            }
        }
        return count;
    }
}