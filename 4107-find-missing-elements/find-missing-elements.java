import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = nums[0];
        int max = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        boolean[] present = new boolean[max + 1];

        for (int num : nums) {
            present[num] = true;
        }

        List<Integer> result = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            if (!present[i]) {
                result.add(i);
            }
        }

        return result;
    }
}