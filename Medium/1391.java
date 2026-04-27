import java.util.*;

class Solution {

    public boolean hasValidPath(int[][] grid) {

        int m = grid.length, n = grid[0].length;

        boolean[][] vis = new boolean[m][n];

        Map<Integer, int[][]> map = new HashMap<>();

        map.put(1, new int[][]{{0,-1},{0,1}});
        map.put(2, new int[][]{{-1,0},{1,0}});
        map.put(3, new int[][]{{0,-1},{1,0}});
        map.put(4, new int[][]{{0,1},{1,0}});
        map.put(5, new int[][]{{0,-1},{-1,0}});
        map.put(6, new int[][]{{0,1},{-1,0}});

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        vis[0][0] = true;

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if (r == m - 1 && c == n - 1) return true;

            int type = grid[r][c];

            for (int[] d : map.get(type)) {

                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nc < 0 || nr >= m || nc >= n || vis[nr][nc])
                    continue;

                int[][] nextDirs = map.get(grid[nr][nc]);

                for (int[] back : nextDirs) {
                    if (nr + back[0] == r && nc + back[1] == c) {
                        vis[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }

        return false;
    }
}
