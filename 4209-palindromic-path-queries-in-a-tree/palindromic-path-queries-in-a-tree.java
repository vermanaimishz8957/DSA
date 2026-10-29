import java.util.*;

class Solution {
    int n;
    int[] bit;

    void upd(int i, int v) {
        for (; i <= n; i += i & -i) bit[i] ^= v;
    }

    void rangeUpd(int l, int r, int v) { // 1-indexed
        upd(l, v);
        if (r + 1 <= n) upd(r + 1, v);
    }

    int pointQuery(int i) {
        int res = 0;
        for (; i > 0; i -= i & -i) res ^= bit[i];
        return res;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        bit = new int[n + 2];

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        final int LOG = 17;
        int[][] up = new int[LOG][n];
        int[] tin = new int[n], tout = new int[n], depth = new int[n];
        int[] parent = new int[n];
        int[] idx = new int[n];

        // iterative DFS for Euler tour
        int timer = 0;
        int[] stk = new int[n];
        int sp = 0;
        stk[sp++] = 0;
        parent[0] = -1;
        tin[0] = ++timer;
        while (sp > 0) {
            int u = stk[sp - 1];
            if (idx[u] < adj[u].size()) {
                int v = adj[u].get(idx[u]++);
                if (v == parent[u]) continue;
                parent[v] = u;
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                tin[v] = ++timer;
                stk[sp++] = v;
            } else {
                tout[u] = timer;
                sp--;
            }
        }

        for (int j = 1; j < LOG; j++)
            for (int i = 0; i < n; i++)
                up[j][i] = up[j - 1][up[j - 1][i]];

        char[] str = s.toCharArray();

        // build initial prefix masks
        for (int i = 0; i < n; i++)
            rangeUpd(tin[i], tout[i], 1 << (str[i] - 'a'));

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);
                if (str[u] != c) {
                    int v = (1 << (str[u] - 'a')) ^ (1 << (c - 'a'));
                    rangeUpd(tin[u], tout[u], v);
                    str[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v, up, depth, LOG);
                int mask = pointQuery(tin[u]) ^ pointQuery(tin[v]) ^ (1 << (str[l] - 'a'));
                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }

    private int lca(int a, int b, int[][] up, int[] depth, int LOG) {
        if (depth[a] < depth[b]) {
            int t = a; a = b; b = t;
        }
        int diff = depth[a] - depth[b];
        for (int j = 0; j < LOG; j++)
            if (((diff >> j) & 1) == 1) a = up[j][a];
        if (a == b) return a;
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][a] != up[j][b]) {
                a = up[j][a];
                b = up[j][b];
            }
        }
        return up[0][a];
    }
}