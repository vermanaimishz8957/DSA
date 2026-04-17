import java.util.*;

class Solution {
    
    private int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            rev = rev * 10 + (num % 10);
            num /= 10;
        }
        return rev;
    }
    
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            
            // If current number already exists as reverse of previous
            if (map.containsKey(nums[i])) {
                int prevIndex = map.get(nums[i]);
                minDist = Math.min(minDist, i - prevIndex);
            }
            
            int rev = reverse(nums[i]);
            
            // Store reversed value
            map.put(rev, i);
        }
        
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}