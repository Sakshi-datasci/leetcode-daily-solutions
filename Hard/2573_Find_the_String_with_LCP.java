class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];

        // Step 1: Greedily assign smallest possible letters
        // If word[i] is unassigned, give it the next available letter
        // If lcp[i][j] > 0, word[j] must equal word[i]
        char next = 'a';
        for (int i = 0; i < n; i++) {
            if (word[i] != 0) continue;
            if (next > 'z') return ""; // ran out of letters
            word[i] = next++;
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    word[j] = word[i];
                }
            }
        }

        // Step 2: Validate using DP
        // lcp[i][j] = 0              if word[i] != word[j]
        // lcp[i][j] = lcp[i+1][j+1] + 1  if word[i] == word[j]
        // We fill from bottom-right to top-left
        int[][] computed = new int[n + 1][n + 1]; // padded with 0s at borders

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] == word[j]) {
                    computed[i][j] = computed[i + 1][j + 1] + 1;
                } else {
                    computed[i][j] = 0;
                }
                if (computed[i][j] != lcp[i][j]) return "";
            }
        }

        return new String(word);
    }
}
