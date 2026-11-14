import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int minSum = Integer.MAX_VALUE;
        int n = nums.size();
        
        // Iterate through all possible starting points of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            
            // Expand the subarray up to length r
            for (int j = i; j < Math.min(n, i + r); j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;
                
                // Check if the length is within [l, r] and the sum is strictly positive
                if (length >= l && length <= r && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        // If minSum wasn't updated, no valid subarray exists
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}