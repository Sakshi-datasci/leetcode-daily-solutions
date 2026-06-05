class Solution {

    static class Node {
        long count;
        long waviness;

        Node(long count, long waviness) {
            this.count = count;
            this.waviness = waviness;
        }
    }

    private char[] digits;
    private Node[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n < 0) return 0;

        digits = Long.toString(n).toCharArray();

        int len = digits.length;

        memo = new Node[len + 1][3][11][11][2];

        Node res = dfs(0, 0, 10, 10, true, false);

        return res.waviness;
    }

    /**
     * pos      -> current index
     * cnt      -> number of digits already chosen in the number,
     *             capped at 2 (0,1,2+)
     * prev2    -> second last digit
     * prev1    -> last digit
     * tight    -> standard digit DP flag
     * started  -> whether number has started
     */
    private Node dfs(int pos, int cnt, int prev2, int prev1,
                     boolean tight, boolean started) {

        if (pos == digits.length) {
            return new Node(1, 0);
        }

        if (!tight) {
            Node saved = memo[pos][cnt][prev2][prev1][started ? 1 : 0];
            if (saved != null) return saved;
        }

        int limit = tight ? digits[pos] - '0' : 9;

        long totalCount = 0;
        long totalWaviness = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nextTight = tight && (d == limit);

            if (!started && d == 0) {
                Node nxt = dfs(
                        pos + 1,
                        0,
                        10,
                        10,
                        nextTight,
                        false
                );

                totalCount += nxt.count;
                totalWaviness += nxt.waviness;
                continue;
            }

            if (!started) {
                Node nxt = dfs(
                        pos + 1,
                        1,
                        10,
                        d,
                        nextTight,
                        true
                );

                totalCount += nxt.count;
                totalWaviness += nxt.waviness;
            } else if (cnt == 1) {

                Node nxt = dfs(
                        pos + 1,
                        2,
                        prev1,
                        d,
                        nextTight,
                        true
                );

                totalCount += nxt.count;
                totalWaviness += nxt.waviness;

            } else {

                int add = 0;

                if ((prev1 > prev2 && prev1 > d) ||
                    (prev1 < prev2 && prev1 < d)) {
                    add = 1;
                }

                Node nxt = dfs(
                        pos + 1,
                        2,
                        prev1,
                        d,
                        nextTight,
                        true
                );

                totalCount += nxt.count;
                totalWaviness += nxt.waviness + nxt.count * add;
            }
        }

        Node ans = new Node(totalCount, totalWaviness);

        if (!tight) {
            memo[pos][cnt][prev2][prev1][started ? 1 : 0] = ans;
        }

        return ans;
    }
}
