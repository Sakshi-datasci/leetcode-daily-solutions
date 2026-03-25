class Solution {
    public boolean canPartitionGrid(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        long totalSum = 0;

        // Step 1: total sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
            }
        }

        // If odd → not possible
        if (totalSum % 2 != 0) return false;

        long target = totalSum / 2;

       
        long rowSum = 0;
        for (int i = 0; i < m - 1; i++) { // ensure non-empty below
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum == target) return true;
        }


        long colSum = 0;
        for (int j = 0; j < n - 1; j++) { // ensure non-empty right side
            for (int i = 0; i < m; i++) {
                colSum += grid[i][j];
            }
            if (colSum == target) return true;
        }

        return false;
    }
}
