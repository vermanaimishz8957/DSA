class Solution {
    HashSet<String> hset;

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        hset = new HashSet<>();

        // Store all prefixes of every number in arr1
        for (int a : arr1) {
            addPrefix(a);
        }

        int max = 0;

        // For each number in arr2, find its longest prefix present in the set
        for (int a : arr2) {
            int len = getMaxPrefix(a);
            max = Math.max(max, len);
        }

        return max;
    }

    // Add all prefixes of num to the set (e.g. 123 -> "1", "12", "123")
    void addPrefix(int num) {
        String s = Integer.toString(num);
        for (int i = 0; i < s.length(); i++)
            hset.add(s.substring(0, i + 1));
    }

    // Return the length of the longest prefix of num found in the set
    int getMaxPrefix(int num) {
        String s = Integer.toString(num);
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if (hset.contains(s.substring(0, i + 1)))
                len = Math.max(len, i + 1);
            else break;
        }
        return len;
    }
}
