class Solution {
    static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        // Length = 2
        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;
            down[v] = m - v;
        }

        for (int len = 3; len <= n; len++) {

            long[] prefixDown = new long[m + 1];
            long[] prefixUp = new long[m + 1];

            for (int i = 1; i <= m; i++) {
                prefixDown[i] = (prefixDown[i - 1] + down[i]) % MOD;
                prefixUp[i] = (prefixUp[i - 1] + up[i]) % MOD;
            }

            long totalUp = prefixUp[m];

            long[] newUp = new long[m + 1];
            long[] newDown = new long[m + 1];

            for (int v = 1; v <= m; v++) {
                newUp[v] = prefixDown[v - 1];

                newDown[v] =
                        (totalUp - prefixUp[v] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        for (int v = 1; v <= m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    }
}
