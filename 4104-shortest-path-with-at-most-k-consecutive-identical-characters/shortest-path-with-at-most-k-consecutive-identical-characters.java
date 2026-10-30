import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        int W = k + 1;
        int[] dist = new int[n * W];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // state encoded as node * W + run
        int start = 0 * W + 1;
        dist[start] = 0;

        // heap entries: {cost, node, run}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], u = cur[1], r = cur[2];

            if (d > dist[u * W + r]) continue;
            if (u == n - 1) return d; // first pop of target is optimal

            for (int[] nxt : adj[u]) {
                int v = nxt[0], w = nxt[1];
                int nr;
                if (labels.charAt(v) == labels.charAt(u)) {
                    nr = r + 1;
                    if (nr > k) continue;
                } else {
                    nr = 1;
                }
                int nd = d + w;
                int idx = v * W + nr;
                if (nd < dist[idx]) {
                    dist[idx] = nd;
                    pq.offer(new int[]{nd, v, nr});
                }
            }
        }

        return -1;
    }
}