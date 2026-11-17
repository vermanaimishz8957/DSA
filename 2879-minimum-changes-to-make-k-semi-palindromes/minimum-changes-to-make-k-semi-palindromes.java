import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        
        // Precompute proper divisors for each length up to n
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            divisors[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 2 * i; j <= n; j += i) {
                divisors[j].add(i);
            }
        }
        
        // cost[i][j] = minimum changes to make substring s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], n * n);
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // Check all valid proper divisors d of len
                for (int d : divisors[len]) {
                    int currentCost = 0;
                    for (int offset = 0; offset < d; offset++) {
                        int l = i + offset;
                        int r = j - d + 1 + offset;
                        while (l < r) {
                            if (arr[l] != arr[r]) {
                                currentCost++;
                            }
                            l += d;
                            r -= d;
                        }
                    }
                    cost[i][j] = Math.min(cost[i][j], currentCost);
                }
            }
        }
        
        // dp[i][j] = minimum changes to partition prefix of length i into j parts
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], n * n);
        }
        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int h = j - 1; h < i; h++) {
                    if (h == 0 && j == 1) {
                        dp[i][j] = Math.min(dp[i][j], cost[0][i - 1]);
                    } else if (dp[h][j - 1] != n * n) {
                        dp[i][j] = Math.min(dp[i][j], dp[h][j - 1] + cost[h][i - 1]);
                    }
                }
            }
        }
        
        return dp[n][k];
    }
}