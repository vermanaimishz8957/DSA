class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // already sorted -> 0 operations
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        // length 2 and unsorted: no proper substring can fix it
        if (n == 2) return -1;

        // find min / max characters and their counts
        char mn = 'z', mx = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }
        int cntMn = 0, cntMx = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == mn) cntMn++;
            if (c == mx) cntMx++;
        }

        // prefix or suffix already in place -> sort the rest in one operation
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) return 1;

        // max only at the front and min only at the back -> 3 operations
        if (s.charAt(0) == mx && s.charAt(n - 1) == mn && cntMx == 1 && cntMn == 1) {
            return 3;
        }

        return 2;
    }
}