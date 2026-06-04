class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;

        for (int num = num1; num <= num2; num++) {
            total += getWaviness(num);
        }

        return total;
    }

    private int getWaviness(int num) {
        char[] digits = String.valueOf(num).toCharArray();

        if (digits.length < 3) {
            return 0;
        }

        int waviness = 0;

        for (int i = 1; i < digits.length - 1; i++) {
            int left = digits[i - 1] - '0';
            int curr = digits[i] - '0';
            int right = digits[i + 1] - '0';

            if ((curr > left && curr > right) ||
                (curr < left && curr < right)) {
                waviness++;
            }
        }

        return waviness;
    }
}
