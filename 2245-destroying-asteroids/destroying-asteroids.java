class Solution {
    public boolean asteroidsDestroyed(int m, int[] nums) {
        Arrays.sort(nums);
        long mass = m;

        for(int i=0; i<nums.length; i++) {
            if(nums[i] <= mass)
                mass += nums[i];
            else return false;
        }
        return true;
    }
}
