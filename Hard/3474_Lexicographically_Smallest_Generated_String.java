class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        char[] word = new char[n + m - 1];
        boolean[] locked = new boolean[n + m - 1];
        
        // Initialize with 'a'
        Arrays.fill(word, 'a');
        
        // Apply all 'T' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (locked[i + j] && word[i + j] != str2.charAt(j)) {
                        return ""; // conflict between two 'T' constraints
                    }
                    word[i + j] = str2.charAt(j);
                    locked[i + j] = true;
                }
            }
        }
        
        // Check and fix 'F' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                // Check if window[i..i+m-1] == str2
                boolean matches = true;
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        matches = false;
                        break;
                    }
                }
                
                if (matches) {
                    // Pick rightmost unlocked position and bump it
                    boolean fixed = false;
                    for (int j = m - 1; j >= 0; j--) {
                        if (!locked[i + j]) {
                            char newChar = (char)(str2.charAt(j) + 1);
                            if (newChar <= 'z') {
                                word[i + j] = newChar;
                                fixed = true;
                                break;
                            }
                        }
                    }
                    if (!fixed) return "";
                }
            }
        }
        
        return new String(word);
    }
}
