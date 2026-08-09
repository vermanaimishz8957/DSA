class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = total stones from i to n-1
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // dp[i][m] = maximum stones current player can collect
        // starting from index i with M = m
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {

                // Can take all remaining piles
                if (2 * m >= n - i) {
                    dp[i][m] = suffix[i];
                    continue;
                }

                int best = 0;

                // Try taking x piles
                for (int x = 1; x <= 2 * m; x++) {

                    // Opponent gets the optimal result from here
                    int opponent = dp[i + x][Math.max(m, x)];

                    // Current player gets total remaining - opponent
                    best = Math.max(best, suffix[i] - opponent);
                }

                dp[i][m] = best;
            }
        }

        return dp[0][1];
    }
}