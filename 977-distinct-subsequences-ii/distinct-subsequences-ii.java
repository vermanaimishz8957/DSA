class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] ends = new long[26];
        
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            long total = 1;
            for (int j = 0; j < 26; j++) {
                total = (total + ends[j]) % MOD;
            }
            ends[idx] = total;
        }
        
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            ans = (ans + ends[i]) % MOD;
        }
        
        return (int) ans;
    }
}