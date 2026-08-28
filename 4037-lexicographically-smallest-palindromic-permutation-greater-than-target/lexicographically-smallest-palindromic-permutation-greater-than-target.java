class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        // Validate if palindromic permutation is possible
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int k = n / 2;
        int[] halfCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        // 1. Check if matching target's left half creates a palindrome strictly greater than target
        int[] targetLeftCnt = new int[26];
        boolean exactValid = true;
        for (int i = 0; i < k; i++) {
            int c = target.charAt(i) - 'a';
            targetLeftCnt[c]++;
            if (targetLeftCnt[c] > halfCnt[c]) {
                exactValid = false;
            }
        }

        if (exactValid) {
            String cand = buildPalindrome(target.substring(0, k), midChar, n % 2 != 0);
            if (cand.compareTo(target) > 0) {
                return cand;
            }
        }

        // 2. Backtrack from position k-1 down to 0 to find the first position where
        // we can place a strictly larger character than target[i]
        for (int i = k - 1; i >= 0; i--) {
            int[] prefixCnt = new int[26];
            boolean prefixValid = true;
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                prefixCnt[c]++;
                if (prefixCnt[c] > halfCnt[c]) {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) continue;

            int[] rem = new int[26];
            for (int c = 0; c < 26; c++) {
                rem[c] = halfCnt[c] - prefixCnt[c];
            }

            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (rem[c] > 0) {
                    rem[c]--;

                    StringBuilder sb = new StringBuilder();
                    sb.append(target, 0, i);
                    sb.append((char) ('a' + c));

                    for (int r = 0; r < 26; r++) {
                        while (rem[r] > 0) {
                            sb.append((char) ('a' + r));
                            rem[r]--;
                        }
                    }

                    return buildPalindrome(sb.toString(), midChar, n % 2 != 0);
                }
            }
        }

        return "";
    }

    private String buildPalindrome(String leftHalf, char midChar, boolean isOdd) {
        StringBuilder sb = new StringBuilder(leftHalf);
        if (isOdd) {
            sb.append(midChar);
        }
        for (int i = leftHalf.length() - 1; i >= 0; i--) {
            sb.append(leftHalf.charAt(i));
        }
        return sb.toString();
    }
}