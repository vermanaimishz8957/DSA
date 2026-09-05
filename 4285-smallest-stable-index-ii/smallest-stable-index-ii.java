class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffixMin = new int[n];
        
        // Precalculate suffix minimums right-to-left
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }
        
        // Track prefix maximums left-to-right and check stability condition
        int prefixMax = 0;
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}