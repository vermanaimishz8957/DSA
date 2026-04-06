class Solution {
    public boolean isAnagram(String s, String t) {
        // Step 1: Length check
        if (s.length() != t.length()) return false;

        // Step 2: Frequency array
        int[] count = new int[26];

        // Step 3: Count characters
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        // Step 4: Check if all zero
        for (int c : count) {
            if (c != 0) return false;
        }

        return true;
    }
}