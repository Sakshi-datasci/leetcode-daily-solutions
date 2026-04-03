import java.util.*;

class Solution {

    public int maxWalls(int[] robots, int[] distance, int[] walls) {

        int n = robots.length;

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = robots[i];
            arr[i][1] = distance[i];
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        Arrays.sort(walls);

        int m = walls.length;

        // Precompute intervals
        int[][] left = new int[n][2];
        int[][] right = new int[n][2];

        for (int i = 0; i < n; i++) {

            int pos = arr[i][0];
            int dist = arr[i][1];

            int leftBound = pos - dist;
            if (i > 0) leftBound = Math.max(leftBound, arr[i - 1][0]);

            int l1 = lowerBound(walls, leftBound);
            int r1 = upperBound(walls, pos) - 1;

            left[i][0] = l1;
            left[i][1] = r1;

            int rightBound = pos + dist;
            if (i < n - 1) rightBound = Math.min(rightBound, arr[i + 1][0]);

            int l2 = lowerBound(walls, pos);
            int r2 = upperBound(walls, rightBound) - 1;

            right[i][0] = l2;
            right[i][1] = r2;
        }

        // DP using map: (lastCoveredIndex → max count)
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(-1, 0);

        for (int i = 0; i < n; i++) {

            Map<Integer, Integer> next = new HashMap<>();

            for (int last : dp.keySet()) {

                int currVal = dp.get(last);

                // Try LEFT
                int l = left[i][0];
                int r = left[i][1];

                int gain = 0;
                if (r >= l) {
                    int start = Math.max(l, last + 1);
                    if (start <= r) gain = r - start + 1;
                }

                int newLast = Math.max(last, r);
                next.put(newLast, Math.max(next.getOrDefault(newLast, 0), currVal + gain));

                // Try RIGHT
                l = right[i][0];
                r = right[i][1];

                gain = 0;
                if (r >= l) {
                    int start = Math.max(l, last + 1);
                    if (start <= r) gain = r - start + 1;
                }

                newLast = Math.max(last, r);
                next.put(newLast, Math.max(next.getOrDefault(newLast, 0), currVal + gain));
            }

            dp = next;
        }

        int ans = 0;
        for (int v : dp.values()) ans = Math.max(ans, v);

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
