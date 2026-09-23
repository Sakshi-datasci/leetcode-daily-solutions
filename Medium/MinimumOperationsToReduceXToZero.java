class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;

        long total = 0;
        for (int num : nums) {
            total += num;
        }

        long target = total - x;

        // If target is negative, it is impossible
        if (target < 0) {
            return -1;
        }

        // If target is 0, we have to remove the whole array
        if (target == 0) {
            return n;
        }

        int left = 0;
        long sum = 0;
        int maxLength = -1;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            // Reduce the window if its sum becomes too large
            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }

            // Found a subarray with sum = target
            if (sum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        if (maxLength == -1) {
            return -1;
        }

        return n - maxLength;
    }
}
