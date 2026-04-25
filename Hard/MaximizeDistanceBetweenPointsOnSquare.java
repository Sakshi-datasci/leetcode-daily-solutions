import java.util.*;

class Solution {

    public int maxDistance(int side, int[][] points, int k) {

        int n = points.length;
        long[] arr = new long[n];

        // Step 1: map to perimeter
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];

            if (y == 0) arr[i] = x;
            else if (x == side) arr[i] = side + y;
            else if (y == side) arr[i] = 2L * side + (side - x);
            else arr[i] = 3L * side + (side - y);
        }

        Arrays.sort(arr);

        // extend array
        long[] ext = new long[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = arr[i];
            ext[i + n] = arr[i] + 4L * side;
        }

        long left = 0, right = 4L * side, ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canPlace(ext, n, k, mid, 4L * side)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (int) ans;
    }

    private boolean canPlace(long[] ext, int n, int k, long dist, long per) {

        for (int i = 0; i < n; i++) {

            int count = 1;
            long first = ext[i];
            long last = ext[i];

            int pos = i;

            // place remaining k-1 points
            for (int step = 1; step < k; step++) {

                long target = last + dist;

                // binary search next valid index
                int next = lowerBound(ext, target, pos + 1, i + n);

                if (next == -1) break;

                last = ext[next];
                pos = next;
                count++;
            }

            if (count == k && (last - first) <= per - dist) {
                return true;
            }
        }

        return false;
    }

    private int lowerBound(long[] arr, long target, int l, int r) {
        int ans = -1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
