class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        // Find the smallest odd number in the array
        for (int num : nums1) {
            if (num % 2 != 0) {
                minOdd = Math.min(minOdd, num);
            }
        }

        // If there are no odd numbers, all elements are already even
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        // If any even number is smaller than the minimum odd number,
        // it cannot be made odd, making uniform parity impossible.
        for (int num : nums1) {
            if (num % 2 == 0 && num < minOdd) {
                return false;
            }
        }

        return true;
    }
}