class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        // Count frequency of each character
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Sort frequencies in ascending order
        java.util.Arrays.sort(freq);

        int ans = 0;
        int idx = 0;

        // Process frequencies from largest to smallest
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;
            ans += freq[i] * (idx / 8 + 1);
            idx++;
        }

        return ans;
    }
}