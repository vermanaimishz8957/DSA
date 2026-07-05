class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;

        int[][] maxScore = new int[n][n];
        int[][] ways = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(maxScore[i], -1);
        }

        maxScore[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                char ch = board.get(i).charAt(j);

                if (ch == 'X' || (i == n - 1 && j == n - 1)) {
                    continue;
                }

                int best = -1;
                long count = 0;

                int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};

                for (int[] d : dirs) {
                    int ni = i + d[0];
                    int nj = j + d[1];

                    if (ni >= n || nj >= n || maxScore[ni][nj] == -1) {
                        continue;
                    }

                    if (maxScore[ni][nj] > best) {
                        best = maxScore[ni][nj];
                        count = ways[ni][nj];
                    } else if (maxScore[ni][nj] == best) {
                        count = (count + ways[ni][nj]) % MOD;
                    }
                }

                if (best == -1) {
                    continue;
                }

                int value = 0;
                if (ch >= '1' && ch <= '9') {
                    value = ch - '0';
                }

                maxScore[i][j] = best + value;
                ways[i][j] = (int) (count % MOD);
            }
        }

        if (ways[0][0] == 0) {
            return new int[]{0, 0};
        }

        return new int[]{maxScore[0][0], ways[0][0]};
    }
}