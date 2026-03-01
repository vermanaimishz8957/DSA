class Solution {
    public int minPartitions(String n) {
        int max = 0;
        // O(n)
        for(int i=0; i<n.length(); i++) {
            int ch = n.charAt(i)-'0';
            max = Math.max(max, ch);
        }
        return max;
    }
}
