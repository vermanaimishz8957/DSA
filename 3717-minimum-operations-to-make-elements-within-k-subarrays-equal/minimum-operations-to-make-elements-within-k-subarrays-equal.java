import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        List<Long> minOps = getMinOps(nums, x);
        
        long[][] memo = new long[n + 1][k + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        return solve(0, k, nums, x, minOps, memo);
    }
    
    private long solve(int i, int k, int[] nums, int x, List<Long> minOps, long[][] memo) {
        if (k == 0) return 0;
        if (i > nums.length - x * k) return (long) 1e18; // Impossible configuration
        if (memo[i][k] != -1) return memo[i][k];
        
        long skip = solve(i + 1, k, nums, x, minOps, memo);
        long pick = minOps.get(i) + solve(i + x, k - 1, nums, x, minOps, memo);
        
        return memo[i][k] = Math.min(skip, pick);
    }
    
    private List<Long> getMinOps(int[] nums, int x) {
        List<Long> minOps = new ArrayList<>();
        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();
        long lowerSum = 0, upperSum = 0;
        int lowerSize = 0, upperSize = 0;
        
        for (int i = 0; i < nums.length; ++i) {
            int val = nums[i];
            if (lowerSize == 0 || val <= lower.lastKey()) {
                lower.merge(val, 1, Integer::sum);
                lowerSum += val;
                lowerSize++;
            } else {
                upper.merge(val, 1, Integer::sum);
                upperSum += val;
                upperSize++;
            }
            
            if (i >= x) {
                int outNum = nums[i - x];
                if (lower.containsKey(outNum)) {
                    lower.merge(outNum, -1, Integer::sum);
                    if (lower.get(outNum) == 0) lower.remove(outNum);
                    lowerSum -= outNum;
                    lowerSize--;
                } else {
                    upper.merge(outNum, -1, Integer::sum);
                    if (upper.get(outNum) == 0) upper.remove(outNum);
                    upperSum -= outNum;
                    upperSize--;
                }
            }
            
            // Balance the two halves
            if (lowerSize < upperSize) {
                int v = upper.firstKey();
                upper.merge(v, -1, Integer::sum);
                if (upper.get(v) == 0) upper.remove(v);
                upperSum -= v;
                upperSize--;
                
                lower.merge(v, 1, Integer::sum);
                lowerSum += v;
                lowerSize++;
            } else if (lowerSize - upperSize > 1) {
                int v = lower.lastKey();
                lower.merge(v, -1, Integer::sum);
                if (lower.get(v) == 0) lower.remove(v);
                lowerSum -= v;
                lowerSize--;
                
                upper.merge(v, 1, Integer::sum);
                upperSum += v;
                upperSize++;
            }
            
            if (i >= x - 1) {
                int median = lower.lastKey();
                long ops = (long) median * lowerSize - lowerSum + upperSum - (long) median * upperSize;
                minOps.add(ops);
            }
        }
        return minOps;
    }
}