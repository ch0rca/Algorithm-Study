package problem;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PG_42627_디스크컨트롤러 {
    public int solution(int[][] jobs) {
        int n = jobs.length;

        int[][] jobsWithIdx = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobsWithIdx[i][0] = jobs[i][0]; // 요청 시각
            jobsWithIdx[i][1] = jobs[i][1]; // 소요 시간
            jobsWithIdx[i][2] = i;          // 작업 번호
        }
        
        Arrays.sort(jobsWithIdx, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[2] - b[2];
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1]; 
            if (a[0] != b[0]) return a[0] - b[0];
            return a[2] - b[2];                  
        });

        int currentTime = 0;
        long totalTurnaround = 0;
        int jobIndex = 0;
        int processedCount = 0;

        while (processedCount < n) {
            while (jobIndex < n && jobsWithIdx[jobIndex][0] <= currentTime) {
                pq.offer(jobsWithIdx[jobIndex]);
                jobIndex++;
            }

            // 큐가 비어있으면 아직 요청되지 않은 다음 작업 시각으로 점프
            if (pq.isEmpty()) {
                currentTime = jobsWithIdx[jobIndex][0];
                continue;
            }

            // 가장 우선순위 높은 작업 꺼내서 처리
            int[] job = pq.poll();
            int requestTime = job[0];
            int duration    = job[1];

            currentTime += duration;
            totalTurnaround += currentTime - requestTime; // 반환 시간 = 완료 시각 - 요청 시각
            processedCount++;
        }

        return (int) (totalTurnaround / n);
    }
}