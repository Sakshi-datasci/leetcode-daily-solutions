import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] result = new int[rows - k + 1][cols - k + 1];

        // Traverse all possible k x k submatrices
        for (int startRow = 0; startRow <= rows - k; startRow++) {
            for (int startCol = 0; startCol <= cols - k; startCol++) {

                List<Integer> elements = new ArrayList<>();

                // Collect elements of k x k submatrix
                for (int i = startRow; i < startRow + k; i++) {
                    for (int j = startCol; j < startCol + k; j++) {
                        elements.add(grid[i][j]);
                    }
                }

                // Sort elements
                Collections.sort(elements);

                // Find minimum absolute difference
                int minDiff = Integer.MAX_VALUE;

                for (int i = 1; i < elements.size(); i++) {
                    int diff = Math.abs(elements.get(i) - elements.get(i - 1));
                    minDiff = Math.min(minDiff, diff);
                }

                result[startRow][startCol] = minDiff;
            }
        }

        return result;
    }
}