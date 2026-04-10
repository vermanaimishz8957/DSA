import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;

        // Map: value -> list of indices
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        int minDistance = Integer.MAX_VALUE;

        // Process each value group
        for (ArrayList<Integer> list : map.values()) {
            int size = list.size();

            if (size >= 3) {
                // Check consecutive triplets
                for (int i = 0; i <= size - 3; i++) {
                    int first = list.get(i);
                    int third = list.get(i + 2);

                    int distance = 2 * (third - first);
                    minDistance = Math.min(minDistance, distance);
                }
            }
        }

        return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
    }
}