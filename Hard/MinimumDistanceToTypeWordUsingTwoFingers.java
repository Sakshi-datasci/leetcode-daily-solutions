import java.util.*;

class Solution {

    public int minimumDistance(String word) {

        int n = word.length();

    
        int[][] dp = new int[27][27];

    
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

      
        dp[26][26] = 0;

        for (char ch : word.toCharArray()) {

            int curr = ch - 'A';
            int[][] newDp = new int[27][27];

            for (int[] row : newDp) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            for (int f1 = 0; f1 <= 26; f1++) {
                for (int f2 = 0; f2 <= 26; f2++) {

                    if (dp[f1][f2] == Integer.MAX_VALUE) continue;

                    int cost = dp[f1][f2];

                  
                    int cost1 = cost + (f1 == 26 ? 0 : dist(f1, curr));
                    newDp[curr][f2] = Math.min(newDp[curr][f2], cost1);

                
                    int cost2 = cost + (f2 == 26 ? 0 : dist(f2, curr));
                    newDp[f1][curr] = Math.min(newDp[f1][curr], cost2);
                }
            }

            dp = newDp;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= 26; i++) {
            for (int j = 0; j <= 26; j++) {
                ans = Math.min(ans, dp[i][j]);
            }
        }

        return ans;
    }

    private int dist(int a, int b) {
        int r1 = a / 6, c1 = a % 6;
        int r2 = b / 6, c2 = b % 6;
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
