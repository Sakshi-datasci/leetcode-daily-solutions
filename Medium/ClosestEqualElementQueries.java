import java.util.*;

class Solution {

    public List<Integer> solveQueries(int[] nums, int[] queries) {

        int n = nums.length;

      
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> ans = new ArrayList<>();

        for (int idx : queries) {

            int val = nums[idx];
            List<Integer> list = map.get(val);

           
            if (list.size() == 1) {
                ans.add(-1);
                continue;
            }

            int size = list.size();

            
            int pos = Collections.binarySearch(list, idx);

           
            int left = list.get((pos - 1 + size) % size);
            int dist1 = Math.abs(idx - left);
            dist1 = Math.min(dist1, n - dist1);

         
            int right = list.get((pos + 1) % size);
            int dist2 = Math.abs(idx - right);
            dist2 = Math.min(dist2, n - dist2);

            ans.add(Math.min(dist1, dist2));
        }

        return ans;
    }
}
