class Solution {
    public int minMoves(int[] nums) {
        int maxVal = nums[0];
        for (int x : nums) {
            if (x > maxVal) {
                maxVal = x;
            }
        }
        
        int totalMoves = 0;
        for (int x : nums) {
            totalMoves += (maxVal - x);
        }
        
        return totalMoves;
    }
}