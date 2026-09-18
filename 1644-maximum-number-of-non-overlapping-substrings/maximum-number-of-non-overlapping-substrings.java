class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        List<int[]> candidates = new ArrayList<>();

        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;
            int start = first[c];
            int end = last[c];
            boolean valid = true;
            int i = start;
            while (i <= end) {
                int ci = s.charAt(i) - 'a';
                if (first[ci] < start) {
                    valid = false;
                    break;
                }
                end = Math.max(end, last[ci]);
                i++;
            }
            if (valid) candidates.add(new int[]{start, end});
        }

        // Keep only leaf intervals (not strictly containing another candidate)
        List<int[]> leaves = new ArrayList<>();
        for (int[] a : candidates) {
            boolean isLeaf = true;
            for (int[] b : candidates) {
                if (a == b) continue;
                if (a[0] <= b[0] && b[1] <= a[1] && !(a[0] == b[0] && a[1] == b[1])) {
                    isLeaf = false;
                    break;
                }
            }
            if (isLeaf) leaves.add(a);
        }

        List<String> result = new ArrayList<>();
        for (int[] interval : leaves) {
            result.add(s.substring(interval[0], interval[1] + 1));
        }
        return result;
    }
}