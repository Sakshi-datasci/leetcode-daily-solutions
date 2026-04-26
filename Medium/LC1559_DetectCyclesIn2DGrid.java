class Solution {

    int m, n;
    boolean[][] visited;

    public boolean containsCycle(char[][] grid) {

        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (!visited[i][j]) {
                    if (dfs(grid, i, j, -1, -1)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] grid, int r, int c, int pr, int pc) {

        visited[r][c] = true;

        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        for (int[] d : dir) {

            int nr = r + d[0];
            int nc = c + d[1];

            if (nr < 0 || nc < 0 || nr >= m || nc >= n)
                continue;

            if (grid[nr][nc] != grid[r][c])
                continue;

        
            if (visited[nr][nc]) {
                if (nr != pr || nc != pc) return true;
            } else {
                if (dfs(grid, nr, nc, r, c)) return true;
            }
        }

        return false;
    }
}
