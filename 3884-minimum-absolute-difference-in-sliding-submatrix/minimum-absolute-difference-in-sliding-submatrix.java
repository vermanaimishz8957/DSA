import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] ans = new int[m - k + 1][n - k + 1];
        
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                
                // Collect distinct elements
                HashSet<Integer> set = new HashSet<>();
                
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        set.add(grid[x][y]);
                    }
                }
                
                // If only one unique value
                if (set.size() <= 1) {
                    ans[i][j] = 0;
                    continue;
                }
                
                // Convert to list and sort
                ArrayList<Integer> list = new ArrayList<>(set);
                Collections.sort(list);
                
                // Find minimum absolute difference
                int minDiff = Integer.MAX_VALUE;
                
                for (int p = 1; p < list.size(); p++) {
                    minDiff = Math.min(minDiff, list.get(p) - list.get(p - 1));
                }
                
                ans[i][j] = minDiff;
            }
        }
        
        return ans;
    }
}