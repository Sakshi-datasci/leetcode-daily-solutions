class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        int NEG_INF = Integer.MIN_VALUE / 2;
        
        
        int[][][] dp = new int[m][n][3];
        
     
        for (int[][] layer : dp)
            for (int[] row : layer)
                Arrays.fill(row, NEG_INF);
        
    
        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0;
            dp[0][0][2] = 0;
        }
        
        int[][] dirs = {{0, 1}, {1, 0}};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    if (dp[i][j][k] == NEG_INF) continue;
                    
                    int cur = dp[i][j][k];
                    
                    for (int[] d : dirs) {
                        int ni = i + d[0], nj = j + d[1];
                        if (ni >= m || nj >= n) continue;
                        
                        int val = coins[ni][nj];
                        
                      
                        if (cur + val > dp[ni][nj][k])
                            dp[ni][nj][k] = cur + val;
                     
                        if (val < 0 && k < 2)
                            if (cur > dp[ni][nj][k + 1])
                                dp[ni][nj][k + 1] = cur;
                    }
                }
            }
        }
        
        return Math.max(dp[m-1][n-1][0], 
               Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}
