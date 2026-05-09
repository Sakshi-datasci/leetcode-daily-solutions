class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        int layers = Math.min(rows, cols) / 2;

        for (int layer = 0; layer < layers; layer++) {

            ArrayList<Integer> elements = new ArrayList<>();

            int top = layer;
            int bottom = rows - layer - 1;
            int left = layer;
            int right = cols - layer - 1;

            // Top row
            for (int j = left; j <= right; j++) {
                elements.add(grid[top][j]);
            }

            // Right column
            for (int i = top + 1; i <= bottom - 1; i++) {
                elements.add(grid[i][right]);
            }

            // Bottom row
            for (int j = right; j >= left; j--) {
                elements.add(grid[bottom][j]);
            }

            // Left column
            for (int i = bottom - 1; i >= top + 1; i--) {
                elements.add(grid[i][left]);
            }

            int size = elements.size();
            int rotate = k % size;

            ArrayList<Integer> rotated = new ArrayList<>();

            // Counter-clockwise rotation
            for (int i = 0; i < size; i++) {
                rotated.add(elements.get((i + rotate) % size));
            }

            int index = 0;

            // Fill Top row
            for (int j = left; j <= right; j++) {
                grid[top][j] = rotated.get(index++);
            }

            // Fill Right column
            for (int i = top + 1; i <= bottom - 1; i++) {
                grid[i][right] = rotated.get(index++);
            }

            // Fill Bottom row
            for (int j = right; j >= left; j--) {
                grid[bottom][j] = rotated.get(index++);
            }

            // Fill Left column
            for (int i = bottom - 1; i >= top + 1; i--) {
                grid[i][left] = rotated.get(index++);
            }
        }

        return grid;
    }
}
