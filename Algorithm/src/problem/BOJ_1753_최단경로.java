package problem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.IOException;

public class BOJ_1753_최단경로 {

    static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int weight) {
            this.v = v;
            this.w = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    private static final int INF = Integer.MAX_VALUE;
    static int V, E, K;
    static List<Node>[] list;
    static int[] dist;

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/problem/test/boj_1753.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        V = Integer.parseInt(st.nextToken()); // 정점 수
        E = Integer.parseInt(st.nextToken()); // 간선 수
        K = Integer.parseInt(in.readLine()); // 시작 정점

        list = new ArrayList[V + 1]; // 1번 인덱스부터 사용
        dist = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        // 리스트에 그래프 정보를 초기화
        // 출발 도착 가중치
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
        }

        // 다익스트라 알고리즘
        dijkstra(K);

        // 1번 정점부터 V번 정점까지 최단 거리 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == INF ? "INF" : dist[i]).append('\n');
        }
        System.out.print(sb);
    }

    static void dijkstra(int st) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        Arrays.fill(dist, INF);

        // 시작 노드까지의 거리는 0
        dist[st] = 0;

        // 시작 노드를 pq에 삽입
        q.offer(new Node(st, 0));

        // pq에 노드가 없을 때까지 반복
        while (!q.isEmpty()) {

            // 하나 꺼내서
            Node cur = q.poll();

            // 새로 선택한 노드의 비용이 이전 비용보다 크다면 무시
            if (cur.w > dist[cur.v]) continue;

            // 새로 꺼낸 노드에 연결된 인접 노드들의 비용을 구해서
            for (Node next : list[cur.v]) {
                int newDist = dist[cur.v] + next.w;

                // 이미 알고 있는 거리보다 더 가깝게 도달할 수 있다면
                if (newDist < dist[next.v]) {
                    // 거리 갱신하고 pq에 넣기
                    dist[next.v] = newDist;
                    q.offer(new Node(next.v, newDist));
                }
            }
        }
    }
}


//public class BOJ_1753_최단경로 {
//
//    static int[] edgeTo;
//    static int[] edgeWeight;
//
//    public static void main(String[] args) throws Exception {
//    	
//    	System.setIn(new FileInputStream("src/problem/test/boj_1753.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int V = Integer.parseInt(st.nextToken()); // 정점 수
//        int E = Integer.parseInt(st.nextToken()); // 간선 수
//        int K = Integer.parseInt(br.readLine());  // 시작 정점
//
//        List<int[]>[] adj = new ArrayList[V + 1];
//        for (int i = 1; i <= V; i++) {
//            adj[i] = new ArrayList<>();
//        }
//
//        for (int i = 0; i < E; i++) {
//            st = new StringTokenizer(br.readLine());
//            int u = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken());
//            int w = Integer.parseInt(st.nextToken());
//            adj[u].add(new int[]{v, w});
//        }
//
//        // 최단 거리 배열 초기화
//        int[] dist = new int[V + 1];
//        Arrays.fill(dist, Integer.MAX_VALUE);
//        dist[K] = 0;
//
//        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
//        pq.offer(new int[]{0, K});
//
//        while (!pq.isEmpty()) {
//            int[] cur = pq.poll();
//            int cost = cur[0];
//            int u = cur[1];
//
//            // 이미 확정된 정점은 스킵
//            if (cost > dist[u]) continue;
//
//            for (int[] edge : adj[u]) {
//                int v = edge[0];
//                int w = edge[1];
//                int newDist = dist[u] + w;
//
//                // 더 짧은 경로 발견 시 갱신
//                if (newDist < dist[v]) {
//                    dist[v] = newDist;
//                    pq.offer(new int[]{newDist, v});
//                }
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i <= V; i++) {
//            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append('\n');
//        }
//        System.out.print(sb);
//    }
//}