class Solution {
    public boolean hasAllCodes(String s, int k) {
        
        int n = s.length();
        if (k > n) return false;
        
        int total = 1 << k;          
        boolean[] seen = new boolean[total];
        
        int mask = total - 1;       
        int hash = 0;
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            
            
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');
            
          
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    count++;
                    
                    if (count == total) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}