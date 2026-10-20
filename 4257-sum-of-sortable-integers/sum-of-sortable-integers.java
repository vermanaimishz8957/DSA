class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int prevMax = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {
                int end = start + k;
                int descents = 0;
                int minVal = Integer.MAX_VALUE;
                int maxVal = Integer.MIN_VALUE;

                for (int j = start; j < end; j++) {
                    minVal = Math.min(minVal, nums[j]);
                    maxVal = Math.max(maxVal, nums[j]);

                    if (j > start && nums[j - 1] > nums[j]) {
                        descents++;
                    }
                }

                if (nums[end - 1] > nums[start]) {
                    descents++;
                }

                if (descents > 1 || prevMax > minVal) {
                    valid = false;
                    break;
                }

                prevMax = maxVal;
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}