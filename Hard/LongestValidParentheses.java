
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int maxLength = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {

                // Case 1: "()"
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2;

                    if (i >= 2) {
                        dp[i] += dp[i - 2];
                    }
                }

                // Case 2: "(...)"
                else {
                    int j = i - dp[i - 1] - 1;

                    if (j >= 0 && s.charAt(j) == '(') {
                        dp[i] = dp[i - 1] + 2;

                        if (j >= 1) {
                            dp[i] += dp[j - 1];
                        }
                    }
                }

                maxLength = Math.max(maxLength, dp[i]);
            }
        }

        return maxLength;
    }
}
