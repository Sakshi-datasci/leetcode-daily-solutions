class Solution {
    public int maximumLength(int[] nums) {

        Map<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        // Special handling for 1
        if (freq.containsKey(1L)) {
            int ones = freq.get(1L);

            if ((ones & 1) == 0) {
                ones--;
            }

            ans = Math.max(ans, ones);
        }

        for (long start : freq.keySet()) {

            if (start == 1) continue;

            long cur = start;
            int len = 0;

            while (freq.getOrDefault(cur, 0) >= 2) {

                len += 2;

                if (cur > 1000000000L) break;

                cur *= cur;

                if (cur > (long) 1e18) break;
            }

            if (freq.getOrDefault(cur, 0) >= 1) {
                len++;
            } else {
                len -= 1;
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}
