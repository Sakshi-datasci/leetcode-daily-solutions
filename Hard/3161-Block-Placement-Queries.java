import java.util.*;

class Solution {

    class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }

            int mid = (start + end) / 2;

            if (idx <= mid) {
                update(node * 2, start, mid, idx, val);
            } else {
                update(node * 2 + 1, mid + 1, end, idx, val);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;

            if (l <= start && end <= r) return tree[node];

            int mid = (start + end) / 2;

            return Math.max(
                query(node * 2, start, mid, l, r),
                query(node * 2 + 1, mid + 1, end, l, r)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {

        int MAX = 0;

        for (int[] q : queries) {
            MAX = Math.max(MAX, q[1]);
        }

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(MAX + 1);

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        SegmentTree seg = new SegmentTree(MAX + 2);

        Integer prev = null;

        for (int pos : obstacles) {
            if (prev != null) {
                seg.update(1, 0, MAX + 1, pos, pos - prev);
            }
            prev = pos;
        }

        List<Boolean> answer = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {

            int[] q = queries[i];

            if (q[0] == 1) {

                int x = q[1];

                Integer left = obstacles.lower(x);
                Integer right = obstacles.higher(x);

                obstacles.remove(x);

                seg.update(1, 0, MAX + 1, right, right - left);
                seg.update(1, 0, MAX + 1, x, 0);

            } else {

                int x = q[1];
                int sz = q[2];

                Integer rightObstacle = obstacles.floor(x);

                int best = 0;

                if (rightObstacle != null) {
                    best = seg.query(
                        1,
                        0,
                        MAX + 1,
                        0,
                        rightObstacle
                    );
                }

                int tailGap = x - rightObstacle;

                answer.add(Math.max(best, tailGap) >= sz);
            }
        }

        Collections.reverse(answer);
        return answer;
    }
}
