class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] cnt = new long[k]; // subarrays ending at previous index, grouped by product % k

        for (int a : nums) {
            long[] next = new long[k];
            int m = a % k;

            // extend every previous subarray by the current element
            for (int r = 0; r < k; r++) {
                if (cnt[r] > 0) {
                    next[(r * m) % k] += cnt[r];
                }
            }

            // the subarray consisting of only the current element
            next[m]++;

            // every subarray ending here contributes to the answer
            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }

            cnt = next;
        }

        return result;
    }
}