class Solution {

    public int maxDistance(int[] colors) {

        int n = colors.length;
        int maxDist = 0;

        // Case 1: fix first element
        for (int j = n - 1; j >= 0; j--) {
            if (colors[j] != colors[0]) {
                maxDist = Math.max(maxDist, j - 0);
                break;
            }
        }

        // Case 2: fix last element
        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, (n - 1) - i);
                break;
            }
        }

        return maxDist;
    }
}
