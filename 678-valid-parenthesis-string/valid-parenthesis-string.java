class Solution {
    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                lo++;
                hi++;
            } else if (c == ')') {
                lo--;
                hi--;
            } else { // '*'
                lo--;
                hi++;
            }
            
            // too many ')' even if every '*' acts as '('
            if (hi < 0) return false;
            
            // open count can't go below zero
            if (lo < 0) lo = 0;
        }
        
        return lo == 0;
    }
}