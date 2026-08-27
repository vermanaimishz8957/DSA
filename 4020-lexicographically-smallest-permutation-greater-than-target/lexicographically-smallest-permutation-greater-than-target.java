class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int[] currentCount = count.clone();
        int bestIndex = -1;
        char bestChar = ' ';

        // Find the largest index i where a larger character can be placed
        for (int i = 0; i < n; i++) {
            char tChar = target.charAt(i);

            // Find the smallest available character > target[i]
            for (int c = (tChar - 'a') + 1; c < 26; c++) {
                if (currentCount[c] > 0) {
                    bestIndex = i;
                    bestChar = (char) ('a' + c);
                    break;
                }
            }

            // Consume target[i] to continue matching prefix
            if (currentCount[tChar - 'a'] > 0) {
                currentCount[tChar - 'a']--;
            } else {
                break;
            }
        }

        if (bestIndex == -1) {
            return "";
        }

        // Reconstruct the string
        StringBuilder sb = new StringBuilder();
        
        // 1. Matched prefix target[0 ... bestIndex - 1]
        sb.append(target, 0, bestIndex);
        
        // 2. Divergent character at bestIndex
        sb.append(bestChar);

        // 3. Count remaining characters
        int[] remCount = count.clone();
        for (int i = 0; i < bestIndex; i++) {
            remCount[target.charAt(i) - 'a']--;
        }
        remCount[bestChar - 'a']--;

        // 4. Append remaining characters in ascending order
        for (int c = 0; c < 26; c++) {
            while (remCount[c] > 0) {
                sb.append((char) ('a' + c));
                remCount[c]--;
            }
        }

        return sb.toString();
    }
}