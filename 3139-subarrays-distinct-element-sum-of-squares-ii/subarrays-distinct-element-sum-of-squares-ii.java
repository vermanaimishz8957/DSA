class Solution {
    static final long MOD = 1_000_000_007L;
    long[] s1, s2, lz;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        s1 = new long[4 * n];
        s2 = new long[4 * n];
        lz = new long[4 * n];

        int[] last = new int[100_001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;
        for (int j = 0; j < n; j++) {
            int p = last[nums[j]];
            update(1, 0, n - 1, p + 1, j);
            last[nums[j]] = j;
            ans = (ans + s2[1]) % MOD;
        }
        return (int) ans;
    }

    private void apply(int o, int l, int r, long k) {
        long len = r - l + 1;
        s2[o] = (s2[o] + 2 * k * s1[o] + k * k % MOD * len) % MOD;
        s1[o] += k * len;   // max ~ n^2 = 1e10, fits in long
        lz[o] += k;
    }

    private void update(int o, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            apply(o, l, r, 1);
            return;
        }
        int m = (l + r) >>> 1;
        if (lz[o] != 0) {
            apply(2 * o, l, m, lz[o]);
            apply(2 * o + 1, m + 1, r, lz[o]);
            lz[o] = 0;
        }
        if (ql <= m) update(2 * o, l, m, ql, qr);
        if (qr > m) update(2 * o + 1, m + 1, r, ql, qr);
        s1[o] = s1[2 * o] + s1[2 * o + 1];
        s2[o] = (s2[2 * o] + s2[2 * o + 1]) % MOD;
    }
}