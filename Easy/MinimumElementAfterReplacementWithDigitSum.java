class Solution {
    public int minElement(int[] nums) {
        int minValue = Integer.MAX_VALUE;

        for (int num : nums) {
            int digitSum = 0;

            while (num > 0) {
                digitSum += num % 10;
                num /= 10;
            }

            minValue = Math.min(minValue, digitSum);
        }

        return minValue;
    }
}
