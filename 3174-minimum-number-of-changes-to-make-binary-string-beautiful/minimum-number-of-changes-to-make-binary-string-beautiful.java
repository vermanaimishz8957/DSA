class Solution {
    public int minChanges(String s) {
        int changes = 0;
        
        // Check each adjacent pair of characters (step by 2)
        for (int i = 0; i < s.length(); i += 2) {
            // If the two characters in the pair are different, 1 change is needed
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        
        return changes;
    }
}