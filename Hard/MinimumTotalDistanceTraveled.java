import java.util.*;

class Solution {

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {

        int n = robot.size();
        int m = factory.length;

        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        long[][] dp = new long[n + 1][m + 1];

        for (long[] row : dp) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

    
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int j = 1; j <= m; j++) {

            int pos = factory[j - 1][0];
            int limit = factory[j - 1][1];

            for (int i = 0; i <= n; i++) {

                dp[i][j] = dp[i][j - 1];

                long cost = 0;

  
                for (int k = 1; k <= limit && i - k >= 0; k++) {

                    cost += Math.abs(robot.get(i - k) - pos);

                    if (dp[i - k][j - 1] != Long.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j],
                                dp[i - k][j - 1] + cost);
                    }
                }
            }
        }

        return dp[n][m];
    }
}
