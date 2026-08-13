class Solution {
    
    class Node {
        int leftChar, rightChar;
        int prefix, suffix, best, len;

        Node(char c) {
            leftChar = rightChar = c - 'a';
            prefix = suffix = best = len = 1;
        }

        Node() {}
    }

    Node[] tree;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        
        int n = s.length();
        int k = queryIndices.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1, s);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].best;
        }

        return ans;
    }

    private void build(int node, int l, int r, String s) {
        
        if (l == r) {
            tree[node] = new Node(s.charAt(l));
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int idx, char c) {
        
        if (l == r) {
            tree[node] = new Node(c);
            return;
        }

        int mid = l + (r - l) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, c);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node a, Node b) {
        
        Node res = new Node();

        res.len = a.len + b.len;
        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        res.prefix = a.prefix;
        res.suffix = b.suffix;

        // Combine prefix
        if (a.prefix == a.len && a.leftChar == b.leftChar) {
            res.prefix = a.len + b.prefix;
        }

        // Combine suffix
        if (b.suffix == b.len && a.rightChar == b.rightChar) {
            res.suffix = b.len + a.suffix;
        }

        // Best answer inside either segment
        res.best = Math.max(a.best, b.best);

        // Best substring crossing the middle
        if (a.rightChar == b.leftChar) {
            res.best = Math.max(res.best, a.suffix + b.prefix);
        }

        return res;
    }
}