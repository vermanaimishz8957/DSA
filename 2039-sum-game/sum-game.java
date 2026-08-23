class Solution {
    public boolean sumGame(String num) {
        int leftSum = 0, rightSum = 0;
        int leftQ = 0, rightQ = 0;
        int n = num.length();

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        // If the number of '?' is odd, Alice always wins.
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        // Difference in fixed digit sums
        int diff = leftSum - rightSum;

        // Bob can balance the game only if the difference
        // can be compensated by the question marks.
        return diff != (rightQ - leftQ) / 2 * 9;
    }
}