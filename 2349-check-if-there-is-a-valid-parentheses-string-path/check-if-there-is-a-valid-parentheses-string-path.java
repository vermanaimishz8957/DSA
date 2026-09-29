import java.util.*;

class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        // Odd path length can never be balanced
        if (((m + n - 1) & 1) == 1) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        int maxBal = (m + n - 1) / 2;   // balance above this can never return to 0
        BitSet[][] dp = new BitSet[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                BitSet cur = new BitSet(maxBal + 2);

                if (i == 0 && j == 0) {
                    cur.set(0);                     // empty balance before the first cell
                } else {
                    if (i > 0) cur.or(dp[i - 1][j]);
                    if (j > 0) cur.or(dp[i][j - 1]);
                }

                BitSet next = new BitSet(maxBal + 2);
                if (grid[i][j] == '(') {
                    // shift every balance up by 1
                    for (int b = cur.nextSetBit(0); b >= 0; b = cur.nextSetBit(b + 1)) {
                        if (b + 1 <= maxBal) next.set(b + 1);
                    }
                } else {
                    // shift every balance down by 1, dropping negatives
                    for (int b = cur.nextSetBit(0); b >= 0; b = cur.nextSetBit(b + 1)) {
                        if (b - 1 >= 0) next.set(b - 1);
                    }
                }
                dp[i][j] = next;
            }
        }
        return dp[m - 1][n - 1].get(0);
    }
}