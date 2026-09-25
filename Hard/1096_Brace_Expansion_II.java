import java.util.*;

class Solution {
    String s;
    int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = solve();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    Set<String> solve() {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            if (s.charAt(index) == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                index++;
            } 
            else {
                Set<String> next = new HashSet<>();

                if (s.charAt(index) == '{') {
                    index++;
                    next = solve();
                    index++;
                } 
                else {
                    next.add(String.valueOf(s.charAt(index)));
                    index++;
                }

                Set<String> temp = new HashSet<>();

                for (String a : current) {
                    for (String b : next) {
                        temp.add(a + b);
                    }
                }

                current = temp;
            }
        }

        result.addAll(current);

        return result;
    }
}
