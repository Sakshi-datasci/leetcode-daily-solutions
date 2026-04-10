import java.util.*;

class Solution {

    public int minimumDistance(int[] nums) {

        int n = nums.length;

        // map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }

        int minDist = Integer.MAX_VALUE;

        // check each value
        for (List<Integer> list : map.values()) {

            if (list.size() < 3) continue;

            // check consecutive triples
            for (int i = 0; i + 2 < list.size(); i++) {

                int first = list.get(i);
                int third = list.get(i + 2);

                int dist = 2 * (third - first);

                minDist = Math.min(minDist, dist);
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
