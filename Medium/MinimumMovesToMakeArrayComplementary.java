class Solution {

    public int minMoves(int[] nums, int limit) {

        int n = nums.length;

        int[] differenceArray = new int[2 * limit + 2];

        for (int index = 0; index < n / 2; index++) {

            int left = nums[index];
            int right = nums[n - 1 - index];

            int minimum = Math.min(left, right);
            int maximum = Math.max(left, right);

            int currentSum = left + right;

            // Default cost = 2 moves
            differenceArray[2] += 2;

            // 1 move range starts
            differenceArray[minimum + 1] -= 1;

            // 0 move at currentSum
            differenceArray[currentSum] -= 1;

            // End of 0 move range
            differenceArray[currentSum + 1] += 1;

            // End of 1 move range
            differenceArray[maximum + limit + 1] += 1;
        }

        int minimumMoves = Integer.MAX_VALUE;

        int currentMoves = 0;

        for (int sum = 2; sum <= 2 * limit; sum++) {

            currentMoves += differenceArray[sum];

            minimumMoves = Math.min(minimumMoves, currentMoves);
        }

        return minimumMoves;
    }
}
