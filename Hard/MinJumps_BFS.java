class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;

        // Build a map: value -> list of indices with that value
        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToIndices
                .computeIfAbsent(arr[i], k -> new ArrayList<>())
                .add(i);
        }

        // BFS
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int curr = queue.poll();

                // Reached last index
                if (curr == n - 1) return steps;

                // Jump to i - 1
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    queue.offer(curr - 1);
                }

                // Jump to i + 1
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    queue.offer(curr + 1);
                }

                // Jump to all same-value indices
                List<Integer> sameVal = valueToIndices.get(arr[curr]);
                if (sameVal != null) {
                    for (int next : sameVal) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                    // ✅ Clear the group to avoid revisiting in future BFS levels
                    valueToIndices.remove(arr[curr]);
                }
            }

            steps++;
        }

        return -1; // unreachable
    }
}
