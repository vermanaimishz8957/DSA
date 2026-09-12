import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> input) {
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < input.size(); ++i) {
            List<Integer> interval = input.get(i);
            intervals.add(new Interval(interval.get(0), interval.get(1), interval.get(2), i));
        }
        
        intervals.sort(Comparator.comparingInt(a -> a.left));
        
        T[][] memo = new T[intervals.size()][5];
        return dp(intervals, memo, 0, 4).selected.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private record T(long weight, List<Integer> selected) {}
    private record Interval(int left, int right, int weight, int originalIndex) {}
    
    private T dp(List<Interval> intervals, T[][] memo, int i, int quota) {
        if (i == intervals.size() || quota == 0) {
            return new T(0, List.of());
        }
        if (memo[i][quota] != null) {
            return memo[i][quota];
        }
        
        T skip = dp(intervals, memo, i + 1, quota);
        
        Interval interval = intervals.get(i);
        int j = findFirstGreater(intervals, i + 1, interval.right);
        T nextRes = dp(intervals, memo, j, quota - 1);
        
        List<Integer> newSelected = new ArrayList<>(nextRes.selected);
        newSelected.add(interval.originalIndex);
        Collections.sort(newSelected);
        T pick = new T(interval.weight + nextRes.weight, newSelected);
        
        if (pick.weight > skip.weight || (pick.weight == skip.weight && compareLists(pick.selected, skip.selected) < 0)) {
            return memo[i][quota] = pick;
        } else {
            return memo[i][quota] = skip;
        }
    }
    
    private int findFirstGreater(List<Interval> intervals, int startFrom, int rightBoundary) {
        int l = startFrom;
        int r = intervals.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (intervals.get(m).left > rightBoundary) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    
    private int compareLists(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
            int cmp = Integer.compare(list1.get(i), list2.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(list1.size(), list2.size());
    }
}