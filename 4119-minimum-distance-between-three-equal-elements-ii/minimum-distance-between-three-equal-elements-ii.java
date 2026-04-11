import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Store indices for each value
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int minDist = Integer.MAX_VALUE;
        
        // Process each group
        for (List<Integer> list : map.values()) {
            if (list.size() < 3) continue;
            
            for (int i = 0; i <= list.size() - 3; i++) {
                int first = list.get(i);
                int third = list.get(i + 2);
                
                int dist = 2 * (third - first);
                minDist = Math.min(minDist, dist);
            }
        }
        
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}