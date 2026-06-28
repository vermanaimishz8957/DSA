class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;

        // Values larger than n can always be decremented to fit within [1, n]
        // so cap all frequencies at index n
        int[] count = new int[n + 1];
        for (int a : arr) count[Math.min(a, n)]++;

        // Greedily assign the highest valid value at each step
        // ans tracks the max value achievable so far (starts at 1 for first element)
        int ans = 1;
        for (int i = 2; i < count.length; i++) {
            // count[i] elements available at value i — each can extend ans by 1
            int next = ans + count[i];
            // Can't exceed i itself (can only decrement, not increment)
            ans = Math.min(i, next);
        }

        return ans;
    }
}