class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] sum = new int[n][m];      // balance: X=+1, Y=-1
        int[][] countX = new int[n][m];   // count of X

        int result = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {

                int val = 0;
                if(grid[i][j] == 'X') val = 1;
                else if(grid[i][j] == 'Y') val = -1;

                int xVal = (grid[i][j] == 'X') ? 1 : 0;

                sum[i][j] = val;
                countX[i][j] = xVal;

                // build prefix sum
                if(i > 0) {
                    sum[i][j] += sum[i-1][j];
                    countX[i][j] += countX[i-1][j];
                }

                if(j > 0) {
                    sum[i][j] += sum[i][j-1];
                    countX[i][j] += countX[i][j-1];
                }

                if(i > 0 && j > 0) {
                    sum[i][j] -= sum[i-1][j-1];
                    countX[i][j] -= countX[i-1][j-1];
                }

                // check conditions
                if(sum[i][j] == 0 && countX[i][j] > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}