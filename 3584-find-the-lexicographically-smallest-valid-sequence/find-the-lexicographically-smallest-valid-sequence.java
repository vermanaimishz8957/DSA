class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // exact[i] = maximum number of characters of word2
        // that can be matched exactly using word1[i...]
        int[] exact = new int[n + 1];

        // oneMismatch[i] = maximum number of characters of word2
        // that can be matched using at most one mismatch
        int[] oneMismatch = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int oldExact = exact[i + 1];
            int oldOne = oneMismatch[i + 1];

            // Exact matching
            exact[i] = oldExact;

            if (oldExact < m &&
                word1.charAt(i) == word2.charAt(m - 1 - oldExact)) {
                exact[i] = oldExact + 1;
            }

            // At most one mismatch
            oneMismatch[i] = oldOne;

            // Current character matches
            if (oldOne < m &&
                word1.charAt(i) == word2.charAt(m - 1 - oldOne)) {
                oneMismatch[i] = Math.max(
                    oneMismatch[i],
                    oldOne + 1
                );
            }

            // Use the one allowed mismatch here
            if (oldExact < m &&
                word1.charAt(i) != word2.charAt(m - 1 - oldExact)) {
                oneMismatch[i] = Math.max(
                    oneMismatch[i],
                    oldExact + 1
                );
            }
        }

        int[] answer = new int[m];

        int prev = -1;
        int usedMismatch = 0;

        // Greedily select the smallest possible index
        for (int j = 0; j < m; j++) {
            char target = word2.charAt(j);
            int remaining = m - j - 1;
            int chosen = -1;

            for (int i = prev + 1; i < n; i++) {

                boolean mismatch = word1.charAt(i) != target;

                // We can use at most one mismatch
                if (usedMismatch + (mismatch ? 1 : 0) > 1) {
                    continue;
                }

                boolean possible;

                if (remaining == 0) {
                    possible = true;
                }
                else if (mismatch || usedMismatch == 1) {
                    // Mismatch is already used,
                    // so remaining characters must match exactly.
                    possible = exact[i + 1] >= remaining;
                }
                else {
                    // Current character matches and we still
                    // have one mismatch available.
                    possible = oneMismatch[i + 1] >= remaining;
                }

                if (possible) {
                    chosen = i;
                    break;
                }
            }

            if (chosen == -1) {
                return new int[0];
            }

            answer[j] = chosen;

            if (word1.charAt(chosen) != target) {
                usedMismatch++;
            }

            prev = chosen;
        }

        return answer;
    }
}