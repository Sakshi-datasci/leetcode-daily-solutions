import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        return checkCuts(grid, m, n, true) || checkCuts(grid, m, n, false);
    }

    private boolean checkCuts(int[][] grid, int m, int n, boolean horizontal) {
        int lines = horizontal ? m : n;
        int otherDim = horizontal ? n : m;

        long[] lineSum = new long[lines];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                lineSum[horizontal ? i : j] += grid[i][j];

        List<Map<Integer, Integer>> lineVals = new ArrayList<>();
        for (int k = 0; k < lines; k++) lineVals.add(new HashMap<>());
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                lineVals.get(horizontal ? i : j).merge(grid[i][j], 1, Integer::sum);

        Map<Integer, Integer> topCounts = new HashMap<>();
        Map<Integer, Integer> botCounts = new HashMap<>();
        long topSum = 0, botSum = 0;

        for (int k = 0; k < lines; k++) {
            botSum += lineSum[k];
            for (Map.Entry<Integer, Integer> e : lineVals.get(k).entrySet())
                botCounts.merge(e.getKey(), e.getValue(), Integer::sum);
        }

        for (int i = 0; i < lines - 1; i++) {
            for (Map.Entry<Integer, Integer> e : lineVals.get(i).entrySet()) {
                int val = e.getKey(), cnt = e.getValue();
                topCounts.merge(val, cnt, Integer::sum);
                int rem = botCounts.get(val) - cnt;
                if (rem == 0) botCounts.remove(val);
                else          botCounts.put(val, rem);
            }
            topSum += lineSum[i];
            botSum -= lineSum[i];

            long diff = topSum - botSum;
            if (diff == 0) return true;

            int topLines = i + 1;
            int botLines = lines - i - 1;

            boolean topIs2D = topLines > 1 && otherDim > 1;
            boolean botIs2D = botLines > 1 && otherDim > 1;

            if (diff > 0 && diff <= 100000) {
                int need = (int) diff;
                if (topCounts.containsKey(need)) {
                    if (topIs2D) return true;
                    // top section endpoints:
                    // horizontal, multi-row single-col: grid[0][0] .. grid[i][0]
                    // horizontal, single-row multi-col: grid[0][0] .. grid[0][n-1]
                    // vertical,   multi-col single-row: grid[0][0] .. grid[0][i]
                    // vertical,   single-col multi-row: grid[0][0] .. grid[m-1][0]
                    int firstCell = grid[0][0];
                    int lastCell;
                    if (horizontal) {
                        lastCell = (otherDim == 1) ? grid[i][0] : grid[0][n - 1];
                    } else {
                        lastCell = (otherDim == 1) ? grid[m - 1][0] : grid[0][i];
                    }
                    if (firstCell == need || lastCell == need) return true;
                }
            } else if (diff < 0 && -diff <= 100000) {
                int need = (int) -diff;
                if (botCounts.containsKey(need)) {
                    if (botIs2D) return true;
                    // bot section endpoints:
                    // horizontal, multi-row single-col: grid[i+1][0] .. grid[m-1][0]
                    // horizontal, single-row multi-col: grid[m-1][0] .. grid[m-1][n-1]
                    // vertical,   multi-col single-row: grid[0][i+1] .. grid[0][n-1]
                    // vertical,   single-col multi-row: grid[0][i+1] .. grid[m-1][i+1]
                    int firstCell, lastCell;
                    if (horizontal) {
                        firstCell = (otherDim == 1) ? grid[i + 1][0]     : grid[m - 1][0];
                        lastCell  = (otherDim == 1) ? grid[m - 1][0]     : grid[m - 1][n - 1];
                    } else {
                        firstCell = (otherDim == 1) ? grid[0][i + 1]     : grid[0][i + 1];
                        lastCell  = (otherDim == 1) ? grid[0][n - 1]     : grid[m - 1][i + 1];
                    }
                    if (firstCell == need || lastCell == need) return true;
                }
            }
        }
        return false;
    }
}
