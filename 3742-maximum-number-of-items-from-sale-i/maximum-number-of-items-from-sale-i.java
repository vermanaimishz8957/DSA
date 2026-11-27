 class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 1500;

        int[] freq = new int[maxFactor + 1];
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] free = new int[n];
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            int count = 0;
            for (int multiple = f; multiple <= maxFactor; multiple += f) {
                count += freq[multiple];
            }
            free[i] = count - 1;
        }

        int[] dp = new int[budget + 1];
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int val = 1 + free[i];
            for (int c = budget; c >= price; c--) {
                dp[c] = Math.max(dp[c], dp[c - price] + val);
            }
        }

        for (int c = minPrice; c <= budget; c++) {
            dp[c] = Math.max(dp[c], dp[c - minPrice] + 1);
        }

        int ans = 0;
        for (int c = 0; c <= budget; c++) {
            ans = Math.max(ans, dp[c]);
        }

        return ans;
    }
}