class Solution {
    public long maximumScore(int[][] grid) {
        final int n = grid.length;
        
        // prefix[j][i] = sum of first i elements in column j
        long[][] prefix = new long[n][n + 1];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                prefix[j][i + 1] = prefix[j][i] + grid[i][j];
            }
        }
        
        // prevPick[i] = max score when previous column's bottom is at row i
        // prevSkip[i] = max score when column before previous had bottom at row i
        long[] prevPick = new long[n + 1];
        long[] prevSkip = new long[n + 1];

        for (int j = 1; j < n; ++j) {
            long[] currPick = new long[n + 1];
            long[] currSkip = new long[n + 1];
            
            for (int curr = 0; curr <= n; ++curr) {
                for (int prev = 0; prev <= n; ++prev) {
                    if (curr > prev) {
                        // Current column goes deeper
                        // White cells in column j-1 (rows prev to curr-1) have black to right
                        final long score = prefix[j - 1][curr] - prefix[j - 1][prev];
                        currPick[curr] = Math.max(currPick[curr], prevSkip[prev] + score);
                        currSkip[curr] = Math.max(currSkip[curr], prevSkip[prev] + score);
                    } else {
                        // Previous column goes deeper or equal
                        // White cells in column j (rows curr to prev-1) have black to left
                        final long score = prefix[j][prev] - prefix[j][curr];
                        currPick[curr] = Math.max(currPick[curr], prevPick[prev] + score);
                        currSkip[curr] = Math.max(currSkip[curr], prevPick[prev]);
                    }
                }
            }
            
            prevPick = currPick;
            prevSkip = currSkip;
        }

        long result = 0;
        for (long val : prevPick) {
            result = Math.max(result, val);
        }
        return result;
    }
}
