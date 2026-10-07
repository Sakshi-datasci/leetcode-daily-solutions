import java.util.*;

class Solution {

    private Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum number of '(' and ')' to remove
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                leftRemove++;
            } 
            else if (ch == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                } 
                else {
                    rightRemove++;
                }
            }
        }

        dfs(s, 0, 0, leftRemove, rightRemove, new StringBuilder());

        return new ArrayList<>(result);
    }

    private void dfs(String s,
                     int index,
                     int balance,
                     int leftRemove,
                     int rightRemove,
                     StringBuilder current) {

        // Invalid prefix
        if (balance < 0) {
            return;
        }

        // End of string
        if (index == s.length()) {

            if (balance == 0 &&
                leftRemove == 0 &&
                rightRemove == 0) {

                result.add(current.toString());
            }

            return;
        }

        char ch = s.charAt(index);

        // Case 1: Character is '('
        if (ch == '(') {

            // Option A: Remove '('
            if (leftRemove > 0) {
                dfs(
                    s,
                    index + 1,
                    balance,
                    leftRemove - 1,
                    rightRemove,
                    current
                );
            }

            // Option B: Keep '('
            current.append('(');

            dfs(
                s,
                index + 1,
                balance + 1,
                leftRemove,
                rightRemove,
                current
            );

            current.deleteCharAt(current.length() - 1);
        }

        // Case 2: Character is ')'
        else if (ch == ')') {

            // Option A: Remove ')'
            if (rightRemove > 0) {
                dfs(
                    s,
                    index + 1,
                    balance,
                    leftRemove,
                    rightRemove - 1,
                    current
                );
            }

            // Option B: Keep ')' only if possible
            if (balance > 0) {

                current.append(')');

                dfs(
                    s,
                    index + 1,
                    balance - 1,
                    leftRemove,
                    rightRemove,
                    current
                );

                current.deleteCharAt(current.length() - 1);
            }
        }

        // Case 3: Letter
        else {

            current.append(ch);

            dfs(
                s,
                index + 1,
                balance,
                leftRemove,
                rightRemove,
                current
            );

            current.deleteCharAt(current.length() - 1);
        }
    }
}
