class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long sum = 0;
        long fsum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            fsum += (long) i * nums[i];
        }

        long max = fsum;

        // f(k) = f(k-1) + sum - n * nums[n-k]
        for (int k = 1; k < n; k++) {
            fsum = fsum + sum - (long) n * nums[n - k];
            max = Math.max(max, fsum);
        }

        return (int) max;
    }
}