class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        
        // The problem is equivalent to choosing 2*k points out of (n + k - 1) points: C(n + k - 1, 2 * k)
        int totalPoints = n + k - 1;
        int r = 2 * k;
        
        if (r > totalPoints) return 0;
        
        long num = 1;
        long den = 1;
        
        for (int i = 1; i <= r; i++) {
            num = (num * (totalPoints - r + i)) % MOD;
            den = (den * i) % MOD;
        }
        
        // Modular inverse using Fermat's Little Theorem: den^(MOD - 2) % MOD
        return (int) ((num * modInverse(den, MOD)) % MOD);
    }
    
    private long modInverse(long a, long m) {
        return power(a, m - 2, m);
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}