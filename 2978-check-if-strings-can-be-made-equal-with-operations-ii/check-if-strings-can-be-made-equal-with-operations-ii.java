class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        int freqeven[] = new int[26];
        int freqodd[] = new int[26];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                freqeven[s1.charAt(i) - 'a']++;  // ✅ s1 incremented
                freqeven[s2.charAt(i) - 'a']--;  // ✅ s2 decremented
            } else {
                freqodd[s1.charAt(i) - 'a']++;   // ✅ correct
                freqodd[s2.charAt(i) - 'a']--;   // ✅ correct
            }
        }

        for (int i = 0; i < 26; i++) {
            if (freqeven[i] != 0 || freqodd[i] != 0) {
                return false;
            }
        }
        return true;
    }
}