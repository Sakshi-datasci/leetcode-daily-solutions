class Solution {
    public boolean canBeEqual(String s1, String s2) {

        // group 1: indices 0,2
        char[] g1_s1 = {s1.charAt(0), s1.charAt(2)};
        char[] g1_s2 = {s2.charAt(0), s2.charAt(2)};

        // group 2: indices 1,3
        char[] g2_s1 = {s1.charAt(1), s1.charAt(3)};
        char[] g2_s2 = {s2.charAt(1), s2.charAt(3)};

        java.util.Arrays.sort(g1_s1);
        java.util.Arrays.sort(g1_s2);
        java.util.Arrays.sort(g2_s1);
        java.util.Arrays.sort(g2_s2);

        return java.util.Arrays.equals(g1_s1, g1_s2) &&
               java.util.Arrays.equals(g2_s1, g2_s2);
    }
}
