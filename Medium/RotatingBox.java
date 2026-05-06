class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int rows = boxGrid.length;
        int cols = boxGrid[0].length;

        // Step 1: Apply gravity (move stones to the right)
        for (int i = 0; i < rows; i++) {
            int emptyPosition = cols - 1; // rightmost position

            for (int j = cols - 1; j >= 0; j--) {
                if (boxGrid[i][j] == '*') {
                    // obstacle resets the empty position
                    emptyPosition = j - 1;
                } else if (boxGrid[i][j] == '#') {
                    // move stone to emptyPosition
                    char temp = boxGrid[i][emptyPosition];
                    boxGrid[i][emptyPosition] = '#';
                    boxGrid[i][j] = temp;
                    emptyPosition--;
                }
            }
        }

        // Step 2: Rotate 90° clockwise
        char[][] result = new char[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = boxGrid[i][j];
            }
        }

        return result;
    }
}
