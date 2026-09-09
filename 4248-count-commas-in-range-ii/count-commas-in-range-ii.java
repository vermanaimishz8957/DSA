class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long k = 3;
        while (k <= 18) {
            long powerOf10 = 1;
            for (int i = 0; i < k; i++) {
                powerOf10 *= 10;
            }
            
            if (powerOf10 > n) {
                break;
            }
            
            totalCommas += (n - powerOf10 + 1);
            k += 3;
        }
        return totalCommas;
    }
}