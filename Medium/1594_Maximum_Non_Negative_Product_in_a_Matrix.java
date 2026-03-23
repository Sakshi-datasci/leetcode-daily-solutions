class Solution {
    public int maxProductPath(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        long[][] maxDp = new long[m][n];
        long[][] minDp = new long[m][n];

        // Initialize start
        maxDp[0][0] = grid[0][0];
        minDp[0][0] = grid[0][0];

        // First column
        for (int i = 1; i < m; i++) {
            maxDp[i][0] = maxDp[i - 1][0] * grid[i][0];
            minDp[i][0] = maxDp[i][0];
        }

        // First row
        for (int j = 1; j < n; j++) {
            maxDp[0][j] = maxDp[0][j - 1] * grid[0][j];
            minDp[0][j] = maxDp[0][j];
        }

        // Fill DP
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                long val = grid[i][j];

                long option1 = maxDp[i - 1][j] * val;
                long option2 = minDp[i - 1][j] * val;
                long option3 = maxDp[i][j - 1] * val;
                long option4 = minDp[i][j - 1] * val;

                maxDp[i][j] = Math.max(Math.max(option1, option2), Math.max(option3, option4));
                minDp[i][j] = Math.min(Math.min(option1, option2), Math.min(option3, option4));
            }
        }

        long result = maxDp[m - 1][n - 1];

        if (result < 0) return -1;

        return (int)(result % 1000000007);
    }
}
