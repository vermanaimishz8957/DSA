import java.util.*;

class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) return false;

            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pb] < rank[pa]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        int low = 0, high = 200000, ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canBuild(n, edges, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canBuild(int n, int[][] edges, int k, int minStrength) {

        DSU dsu = new DSU(n);
        int used = 0;
        int upgrades = 0;

        List<int[]> optional = new ArrayList<>();

        // Process mandatory edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            if (must == 1) {
                if (s < minStrength) return false;
                if (!dsu.union(u, v)) return false;
                used++;
            } else {
                optional.add(e);
            }
        }

        // Sort optional edges by strength descending
        optional.sort((a, b) -> b[2] - a[2]);

        for (int[] e : optional) {
            int u = e[0], v = e[1], s = e[2];

            if (dsu.find(u) == dsu.find(v)) continue;

            if (s >= minStrength) {
                dsu.union(u, v);
                used++;
            } 
            else if (2 * s >= minStrength && upgrades < k) {
                upgrades++;
                dsu.union(u, v);
                used++;
            }

            if (used == n - 1) return true;
        }

        return used == n - 1;
    }
}