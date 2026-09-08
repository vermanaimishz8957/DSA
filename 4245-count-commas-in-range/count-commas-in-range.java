class Solution {
    public int countCommas(int n) {
        int totalCommas = 0;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            totalCommas += (s.length() - 1) / 3;
        }
        return totalCommas;
    }
}