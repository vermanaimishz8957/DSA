class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0, maxIdx = 0;
        
        
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        
        // Identify the left-most and right-most target indices
        int first = Math.min(minIdx, maxIdx);
        int second = Math.max(minIdx, maxIdx);
        
       
        int removeFront = second + 1;
        
       
        int removeBack = n - first;

        int removeBothSides = (first + 1) + (n - second);
        
        return Math.min(removeFront, Math.min(removeBack, removeBothSides));
    }
}