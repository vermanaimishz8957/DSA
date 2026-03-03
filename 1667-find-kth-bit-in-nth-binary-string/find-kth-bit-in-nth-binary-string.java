class Solution {
    public char findKthBit(int n, int k) {
        // Base case
        if (n == 1) return '0';

        long len = (1L << n) - 1;      // 2^n - 1 (use long to prevent overflow)
        long mid = (len / 2) + 1;      // middle position

        if (k == mid) 
            return '1';

        if (k < mid) 
            return findKthBit(n - 1, k);

        // k > mid
        long newK = len - k + 1;       // mirror position
        char bit = findKthBit(n - 1, (int)newK);

        // invert result
        return (bit == '0') ? '1' : '0';
    }
}