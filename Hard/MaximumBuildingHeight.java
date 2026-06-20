import java.util.*;

class Solution {

    public int maxBuilding(int n, int[][] restrictions) {

        List<int[]> list = new ArrayList<>();

        list.add(new int[]{1, 0});

        for (int[] r : restrictions) {
            list.add(r);
        }

        list.add(new int[]{n, n - 1});

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        int m = list.size();

        // Left to Right
        for (int i = 1; i < m; i++) {

            int dist = list.get(i)[0] - list.get(i - 1)[0];

            list.get(i)[1] = Math.min(
                    list.get(i)[1],
                    list.get(i - 1)[1] + dist
            );
        }

        // Right to Left
        for (int i = m - 2; i >= 0; i--) {

            int dist = list.get(i + 1)[0] - list.get(i)[0];

            list.get(i)[1] = Math.min(
                    list.get(i)[1],
                    list.get(i + 1)[1] + dist
            );
        }

        long ans = 0;

        for (int i = 1; i < m; i++) {

            long id1 = list.get(i - 1)[0];
            long h1 = list.get(i - 1)[1];

            long id2 = list.get(i)[0];
            long h2 = list.get(i)[1];

            long dist = id2 - id1;

            long peak = (h1 + h2 + dist) / 2;

            ans = Math.max(ans, peak);
        }

        return (int) ans;
    }
}
