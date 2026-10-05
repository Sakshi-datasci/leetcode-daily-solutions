class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {

            if (c == '(') {
                // Start a new group
                stack.push(0);
            } else {
                // Score inside the current pair
                int inside = stack.pop();

                // () -> 1
                // (A) -> 2 * A
                int score = (inside == 0) ? 1 : 2 * inside;

                // Add this score to the outer group
                int outer = stack.pop();
                stack.push(outer + score);
            }
        }

        return stack.pop();
    }
}
