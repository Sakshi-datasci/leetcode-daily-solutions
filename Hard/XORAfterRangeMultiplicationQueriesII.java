class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        final long MOD = 1_000_000_007L;
        int n = nums.length;
        int[][] bravexuneth = queries;
        int B = (int) Math.sqrt(n);

        long[] diff1 = new long[n + 1];
        java.util.Arrays.fill(diff1, 1L);


        @SuppressWarnings("unchecked")
        java.util.List<int[]>[] byStride = new java.util.ArrayList[B + 1];
        for (int k = 2; k <= B; k++) byStride[k] = new java.util.ArrayList<>();

        for (int[] q : bravexuneth) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k == 1) {
                diff1[l] = diff1[l] * v % MOD;
                diff1[r + 1] = diff1[r + 1] * modInverse(v, MOD) % MOD;
            } else if (k <= B) {
                byStride[k].add(new int[]{l, r, v});
            } else {
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (int)((long) nums[idx] * v % MOD);
                }
            }
        }

     
        for (int k = 2; k <= B; k++) {
            if (byStride[k].isEmpty()) continue;
            int slots = n / k + 2;
            long[] d = new long[slots];

            for (int res = 0; res < k; res++) {
                java.util.Arrays.fill(d, 1L);

               
                for (int[] q : byStride[k]) {
                    int l = q[0], r = q[1], v = q[2];
                    if (l % k != res) continue;
                    int slotL = l / k;
                    int lastIdx = l + ((r - l) / k) * k;
                    int slotR = lastIdx / k;
                    d[slotL] = d[slotL] * v % MOD;
                    if (slotR + 1 < slots) {
                        d[slotR + 1] = d[slotR + 1] * modInverse(v, MOD) % MOD;
                    }
                }

             
                long running = 1L;
                int slot = 0;
                for (int i = res; i < n; i += k) {
                    running = running * d[slot++] % MOD;
                    if (running != 1L) {
                        nums[i] = (int)((long) nums[i] * running % MOD);
                    }
                }
            }
        }

      
        long running = 1L;
        int result = 0;
        for (int i = 0; i < n; i++) {
            running = running * diff1[i] % MOD;
            result ^= (int)((long) nums[i] * running % MOD);
        }
        return result;
    }

    private long modInverse(long a, long mod) { return modPow(a, mod - 2, mod); }

    private long modPow(long base, long exp, long mod) {
        long result = 1L;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }
}
