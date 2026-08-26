import java.util.ArrayList;
import java.util.List;

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        List<Integer> ones = new ArrayList<>();
        
        // Collect the index of every '1' in the string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }
        
        // If there are fewer than k ones, no valid substring exists
        if (ones.size() < k) {
            return "";
        }
        
        int minLen = Integer.MAX_VALUE;
        String ans = "";
        
        // Check every window containing exactly k '1's
        for (int i = 0; i <= ones.size() - k; i++) {
            int start = ones.get(i);
            int end = ones.get(i + k - 1);
            int len = end - start + 1;
            String sub = s.substring(start, end + 1);
            
            if (len < minLen) {
                minLen = len;
                ans = sub;
            } else if (len == minLen) {
                if (sub.compareTo(ans) < 0) {
                    ans = sub;
                }
            }
        }
        
        return ans;
    }
}