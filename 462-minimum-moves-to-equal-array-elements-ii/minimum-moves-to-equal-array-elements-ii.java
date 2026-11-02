import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // Step 1: Sort the array to find the median
        Arrays.sort(nums);
        
        // Step 2: The median element minimizes the total absolute differences
        int median = nums[nums.length / 2];
        
        // Step 3: Calculate the total moves required
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}