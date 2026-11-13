import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, long k) {
        long ans = 0;
        long cost = 0;
        // Deque stores long arrays representing [num, count]
        Deque<long[]> dq = new ArrayDeque<>();
        int n = nums.length;
        int j = n - 1;
        
        for (int i = n - 1; i >= 0; --i) {
            long num = nums[i];
            long count = 1;
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                long[] prev = dq.pollLast();
                long nextNum = prev[0];
                long nextCount = prev[1];
                count += nextCount;
                cost += (num - nextNum) * nextCount;
            }
            dq.offerLast(new long[]{num, count});
            
            while (cost > k) {
                long[] front = dq.pollFirst();
                long rightmostNum = front[0];
                long rightmostCount = front[1];
                cost -= (rightmostNum - nums[j]);
                j--;
                if (rightmostCount > 1) {
                    dq.offerFirst(new long[]{rightmostNum, rightmostCount - 1});
                }
            }
            ans += (j - i + 1);
        }
        return ans;
    }
}