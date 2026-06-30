class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] last = new int[3]; // last seen index of 'a', 'b', 'c'
        Arrays.fill(last, -1);
        int ans = 0;

        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;

            // Earliest of the three last-seen indices = leftmost index needed
            // Any starting point in [0, minLast] produces a valid substring ending at i
            int minLast = Math.min(last[0], Math.min(last[1], last[2]));
            ans += (1 + minLast);
        }

        return ans;
    }
}