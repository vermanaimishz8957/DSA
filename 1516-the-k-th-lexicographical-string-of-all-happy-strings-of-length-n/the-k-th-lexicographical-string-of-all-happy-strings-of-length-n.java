class Solution {

    private int count = 0;
    private String result = "";

    public String getHappyString(int n, int k) {
        dfs(n, k, "");
        return result;
    }

    private void dfs(int n, int k, String current) {
        if (current.length() == n) {
            count++;
            if (count == k) {
                result = current;
            }
            return;
        }

        char[] chars = {'a', 'b', 'c'};

        for (char ch : chars) {
            if (current.length() > 0 && current.charAt(current.length() - 1) == ch) {
                continue;
            }

            dfs(n, k, current + ch);

            if (!result.equals("")) return; // stop early when kth found
        }
    }
}