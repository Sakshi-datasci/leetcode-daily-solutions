class Solution {

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];

        int answer = 1;

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dfs(i, arr, d, dp));
        }

        return answer;
    }

    private int dfs(int index, int[] arr, int d, int[] dp) {

        // If already calculated
        if (dp[index] != 0) {
            return dp[index];
        }

        int maxVisit = 1;

        // Check left side
        for (int i = index - 1; i >= Math.max(0, index - d); i--) {

            // Stop if higher or equal element found
            if (arr[i] >= arr[index]) {
                break;
            }

            maxVisit = Math.max(maxVisit,
                    1 + dfs(i, arr, d, dp));
        }

        // Check right side
        for (int i = index + 1; i <= Math.min(arr.length - 1, index + d); i++) {

            // Stop if higher or equal element found
            if (arr[i] >= arr[index]) {
                break;
            }

            maxVisit = Math.max(maxVisit,
                    1 + dfs(i, arr, d, dp));
        }

        dp[index] = maxVisit;

        return dp[index];
    }
}
