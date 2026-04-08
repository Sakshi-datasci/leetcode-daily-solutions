class Solution {

    public int xorAfterQueries(int[] nums, int[][] queries) {

        int MOD = 1_000_000_007;

        // Process each query
        for (int[] q : queries) {

            int li = q[0];
            int ri = q[1];
            int ki = q[2];
            int vi = q[3];

            // Apply updates
            for (int idx = li; idx <= ri; idx += ki) {
                long val = (long) nums[idx] * vi; // prevent overflow
                nums[idx] = (int) (val % MOD);
            }
        }

        // Compute XOR
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }

        return result;
    }
}
