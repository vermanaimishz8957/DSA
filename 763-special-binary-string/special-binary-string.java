import java.util.*;

class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) return s;
        
        int balance = 0;
        int start = 0;
        List<String> parts = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                balance++;
            } else {
                balance--;
            }
            
            // When balance becomes 0, we found a special substring
            if (balance == 0) {
                // Recursively solve the inner substring
                String inner = makeLargestSpecial(s.substring(start + 1, i));
                parts.add("1" + inner + "0");
                start = i + 1;
            }
        }
        
        // Sort in descending lexicographical order
        Collections.sort(parts, Collections.reverseOrder());
        
        // Build final result
        StringBuilder result = new StringBuilder();
        for (String str : parts) {
            result.append(str);
        }
        
        return result.toString();
    }
}