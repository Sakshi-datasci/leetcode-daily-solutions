import java.util.*;

class Solution {

    public int minimumDistance(int[] nums) {

        int n = nums.length;

        // map value -> queue of last 3 indices
        Map<Integer, Deque<Integer>> map = new HashMap<>();

        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            int val = nums[i];

            map.putIfAbsent(val, new ArrayDeque<>());
            Deque<Integer> dq = map.get(val);

            dq.addLast(i);

            // keep only last 3 indices
            if (dq.size() > 3) {
                dq.pollFirst();
            }

            if (dq.size() == 3) {

                int first = dq.peekFirst();
                int third = dq.peekLast();

                int dist = 2 * (third - first);

                minDist = Math.min(minDist, dist);
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
