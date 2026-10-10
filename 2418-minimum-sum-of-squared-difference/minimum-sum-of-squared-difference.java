class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.abs(nums1[i] - nums2[i]));
        }

        long[] cnt = new long[max + 2];
        for (int i = 0; i < n; i++) {
            cnt[Math.abs(nums1[i] - nums2[i])]++;
        }

        long k = (long) k1 + k2;
        long c = 0;   // elements currently at the top level v
        int v = max;

        // Lower the top level as far as the budget allows
        for (; v > 0; v--) {
            c += cnt[v];
            if (c == 0) continue;
            if (k >= c) {
                k -= c;   // lower all c elements from v to v-1
            } else {
                break;    // can only lower k of them
            }
        }

        // Everything reduced to 0
        if (v == 0) return 0;

        long result = 0;

        // At top level v: (c - k) stay at v, k drop to v-1
        result += (c - k) * (long) v * v;
        result += k * (long) (v - 1) * (v - 1);

        // Elements originally below v are untouched
        for (int i = v - 1; i >= 1; i--) {
            result += cnt[i] * (long) i * i;
        }

        return result;
    }
}