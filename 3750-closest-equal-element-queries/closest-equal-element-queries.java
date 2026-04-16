import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        // Step 1: value -> sorted indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> result = new ArrayList<>();

        // Step 2: process queries
        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);

            // If only one occurrence
            if (list.size() == 1) {
                result.add(-1);
                continue;
            }

            // Binary search to find position of q
            int pos = Collections.binarySearch(list, q);

            int size = list.size();

            // Neighbors in circular index list
            int leftIdx = list.get((pos - 1 + size) % size);
            int rightIdx = list.get((pos + 1) % size);

            // Compute circular distances
            int distLeft = Math.abs(q - leftIdx);
            distLeft = Math.min(distLeft, n - distLeft);

            int distRight = Math.abs(q - rightIdx);
            distRight = Math.min(distRight, n - distRight);

            result.add(Math.min(distLeft, distRight));
        }

        return result;
    }
}