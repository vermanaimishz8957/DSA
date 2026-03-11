class Solution {
    public int bitwiseComplement(int n) {
        // base case 
        if(n==0)
            return 1;
        // O(32) = O(1)
        int hbit = Integer.highestOneBit(n)
<<1;
        return n ^ (hbit - 1);
    }
}