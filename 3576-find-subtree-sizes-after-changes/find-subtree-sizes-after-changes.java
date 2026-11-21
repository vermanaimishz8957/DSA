import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origTree = new ArrayList[n];
        List<Integer>[] newTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        int[] lastCharAncestor = new int[26];
        Arrays.fill(lastCharAncestor, -1);

        buildNewTree(0, parent, s, origTree, newTree, lastCharAncestor);

        int[] ans = new int[n];
        computeSubtreeSizes(0, newTree, ans);
        return ans;
    }

    private void buildNewTree(
            int u,
            int[] parent,
            String s,
            List<Integer>[] origTree,
            List<Integer>[] newTree,
            int[] lastCharAncestor
    ) {
        int c = s.charAt(u) - 'a';
        if (u > 0) {
            int newParent = (lastCharAncestor[c] != -1) ? lastCharAncestor[c] : parent[u];
            newTree[newParent].add(u);
        }

        int prev = lastCharAncestor[c];
        lastCharAncestor[c] = u;

        for (int v : origTree[u]) {
            buildNewTree(v, parent, s, origTree, newTree, lastCharAncestor);
        }

        lastCharAncestor[c] = prev;
    }

    private int computeSubtreeSizes(int u, List<Integer>[] newTree, int[] ans) {
        int size = 1;
        for (int v : newTree[u]) {
            size += computeSubtreeSizes(v, newTree, ans);
        }
        ans[u] = size;
        return size;
    }
}