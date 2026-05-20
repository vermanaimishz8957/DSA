class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        long bitsA = 0l, bitsB = 0l;
        int n = A.length;
        int ans[] = new int[n];

        for(int i=0; i<n; i++) {
            bitsA = bitsA | (1L << A[i]);
            bitsB = bitsB | (1L << B[i]);

            ans[i] = Long.bitCount(bitsA & bitsB);
        }

        return ans;
    }
}