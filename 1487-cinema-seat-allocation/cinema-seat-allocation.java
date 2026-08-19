import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        // Store reserved seats for each row
        for (int[] seat : reservedSeats) {
            map.putIfAbsent(seat[0], new HashSet<>());
            map.get(seat[0]).add(seat[1]);
        }
        
        // Initially, every row can accommodate 2 families
        int result = 2 * n;
        
        // Process only rows having reserved seats
        for (int row : map.keySet()) {
            Set<Integer> set = map.get(row);
            
            boolean left = !(set.contains(2) || set.contains(3) ||
                             set.contains(4) || set.contains(5));
            
            boolean right = !(set.contains(6) || set.contains(7) ||
                              set.contains(8) || set.contains(9));
            
            boolean middle = !(set.contains(4) || set.contains(5) ||
                               set.contains(6) || set.contains(7));
            
            if (left && right) {
                // Two families can still be placed
                continue;
            } 
            else if (left || right || middle) {
                // Only one family can be placed
                result--;
            } 
            else {
                // No family can be placed
                result -= 2;
            }
        }
        
        return result;
    }
}