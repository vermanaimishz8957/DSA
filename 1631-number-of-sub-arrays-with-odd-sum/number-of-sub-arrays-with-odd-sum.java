class Solution {
    public int numOfSubarrays(int[] arr) {
        long MOD = 1_000_000_007;
        long oddCount = 0, evenCount = 1; // Base case: prefix sum 0 is even
        long currentSum = 0;
        long result = 0;

        for (int num : arr) {
            currentSum += num;
            
            if (currentSum % 2 == 1) {
                // Current prefix sum is odd; pair it with all previous even prefix sums
                result = (result + evenCount) % MOD;
                oddCount++;
            } else {
                // Current prefix sum is even; pair it with all previous odd prefix sums
                result = (result + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) result;
    }
}