class Solution {

    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        int size = 2 * m;

        long[] start = new long[size];

        for (int v = 0; v < m; v++) {

            start[v] = v;                 // up
            start[m + v] = m - 1 - v;    // down
        }

        if (n == 2) {
            long ans = 0;
            for (long x : start) {
                ans = (ans + x) % MOD;
            }
            return (int) ans;
        }

        long[][] T = new long[size][size];

        for (int v = 0; v < m; v++) {

            for (int x = 0; x < v; x++) {
                T[v][m + x] = 1;
            }

            for (int x = v + 1; x < m; x++) {
                T[m + v][x] = 1;
            }
        }

        long[][] P = power(T, n - 2);

        long[] result = multiply(P, start);

        long ans = 0;

        for (long val : result) {
            ans = (ans + val) % MOD;
        }

        return (int) ans;
    }

    private long[][] power(long[][] mat, long exp) {

        int n = mat.length;

        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {

            if ((exp & 1) == 1) {
                res = multiply(res, mat);
            }

            mat = multiply(mat, mat);

            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {

        int n = A.length;

        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {

            for (int k = 0; k < n; k++) {

                if (A[i][k] == 0) continue;

                for (int j = 0; j < n; j++) {

                    if (B[k][j] == 0) continue;

                    C[i][j] =
                        (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }

    private long[] multiply(long[][] A, long[] v) {

        int n = A.length;

        long[] res = new long[n];

        for (int i = 0; i < n; i++) {

            long sum = 0;

            for (int j = 0; j < n; j++) {
                sum = (sum + A[i][j] * v[j]) % MOD;
            }

            res[i] = sum;
        }

        return res;
    }
}
