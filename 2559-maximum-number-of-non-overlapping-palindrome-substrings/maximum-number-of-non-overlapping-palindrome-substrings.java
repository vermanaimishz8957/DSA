class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if (n < k) return 0;
        
        boolean[][] isPal = new boolean[n][n];
        // Build isPal table: isPal[i][j] true if s[i..j] is a palindrome
        for (int i = n - 1; i >= 0; i--) {
            isPal[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    isPal[i][j] = (j - i < 2) || isPal[i + 1][j - 1];
                } else {
                    isPal[i][j] = false;
                }
            }
        }
        
        int[] dp = new int[n + 1];
        // dp[i] = max number of valid substrings selectable in s[i..n-1]
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1]; // skip index i, don't start a substring here
            
            // try length k starting at i
            if (i + k <= n && isPal[i][i + k - 1]) {
                dp[i] = Math.max(dp[i], 1 + dp[i + k]);
            }
            // try length k+1 starting at i
            if (i + k + 1 <= n && isPal[i][i + k]) {
                dp[i] = Math.max(dp[i], 1 + dp[i + k + 1]);
            }
        }
        
        return dp[0];
    }
}