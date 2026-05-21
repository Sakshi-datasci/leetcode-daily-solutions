class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<String> prefixes = new HashSet<>();
        
        // Store all prefixes of arr1 numbers
        for (int num : arr1) {
            String s = String.valueOf(num);
            
            for (int i = 1; i <= s.length(); i++) {
                prefixes.add(s.substring(0, i));
            }
        }
        
        int maxLength = 0;
        
        // Check prefixes of arr2 numbers
        for (int num : arr2) {
            String s = String.valueOf(num);
            
            for (int i = 1; i <= s.length(); i++) {
                String prefix = s.substring(0, i);
                
                if (prefixes.contains(prefix)) {
                    maxLength = Math.max(maxLength, i);
                }
            }
        }
        
        return maxLength;
    }
}
