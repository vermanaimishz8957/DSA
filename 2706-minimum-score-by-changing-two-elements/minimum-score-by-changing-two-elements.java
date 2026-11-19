import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int case1 = nums[n - 1] - nums[2]; // Change 2 smallest elements
        int case2 = nums[n - 3] - nums[0]; // Change 2 largest elements
        int case3 = nums[n - 2] - nums[1]; // Change 1 smallest and 1 largest element

        return Math.min(case1, Math.min(case2, case3));
    }
}