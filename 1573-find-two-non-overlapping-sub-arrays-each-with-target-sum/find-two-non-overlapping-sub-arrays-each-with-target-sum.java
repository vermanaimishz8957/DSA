class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n]; // dp[i] = min length of valid subarray ending at or before i
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        int left = 0, sum = 0;
        int best = Integer.MAX_VALUE; // best single subarray length found so far
        int res = Integer.MAX_VALUE;  // answer: min sum of two non-overlapping lengths
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // shrink window while sum exceeds target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            if (sum == target) {
                int len = right - left + 1;
                
                // combine with best subarray found before 'left'
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    res = Math.min(res, dp[left - 1] + len);
                }
                
                best = Math.min(best, len);
            }
            
            dp[right] = best; // store best length ending at or before 'right'
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}