import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] result = new int[rows - k + 1][cols - k + 1];

        for (int startRow = 0; startRow <= rows - k; startRow++) {
            for (int startCol = 0; startCol <= cols - k; startCol++) {

                Set<Integer> uniqueSet = new HashSet<>();

                for (int i = startRow; i < startRow + k; i++) {
                    for (int j = startCol; j < startCol + k; j++) {
                        uniqueSet.add(grid[i][j]);
                    }
                }

                // Convert to list
                List<Integer> elements = new ArrayList<>(uniqueSet);

                // If only one unique element → answer = 0
                if (elements.size() == 1) {
                    result[startRow][startCol] = 0;
                    continue;
                }

                Collections.sort(elements);

                int minDiff = Integer.MAX_VALUE;

                for (int i = 1; i < elements.size(); i++) {
                    int diff = elements.get(i) - elements.get(i - 1);
                    minDiff = Math.min(minDiff, diff);
                }

                result[startRow][startCol] = minDiff;
            }
        }

        return result;
    }
}
