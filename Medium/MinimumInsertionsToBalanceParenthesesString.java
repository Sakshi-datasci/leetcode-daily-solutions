
class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // If the next character is ')',
                // use it as the second closing bracket.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert one ')' to make a pair.
                    insertions++;
                }

                // This pair needs one matching '('.
                if (open > 0) {
                    open--;
                } else {
                    // Insert a '(' before this closing pair.
                    insertions++;
                }
            }
        }

        // Each unmatched '(' requires two ')'.
        insertions += open * 2;

        return insertions;
    }
}
