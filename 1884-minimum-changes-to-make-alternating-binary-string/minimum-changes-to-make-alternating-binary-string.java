class Solution {
    public int minOperations(String s) {
        int zerostart = 0, onestart = 0;

        for(int i=0; i<s.length(); i++) {
            // O(n)
            // O(1)
            // 0 0 0 0 0
            // 0 1 2 3 4
            // zerostart = 1,2 [01010]
            // onstart = 1,2,3 [10101]
            char ch = s.charAt(i);
            if(i%2==0) {
                if(ch=='0')
                    onestart++;
                else zerostart++;
            }
            else {
                if(ch=='0') 
                    zerostart++;
                else onestart++;
            }

        }
        return Math.min(zerostart, onestart);
    }
}