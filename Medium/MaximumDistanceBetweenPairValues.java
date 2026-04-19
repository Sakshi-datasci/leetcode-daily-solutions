class Solution {

    public int maxDistance(int[] nums1, int[] nums2) {

        int i = 0, j = 0;
        int maxDist = 0;

        int n1 = nums1.length;
        int n2 = nums2.length;

        while (i < n1 && j < n2) {

            if (nums1[i] <= nums2[j]) {
                maxDist = Math.max(maxDist, j - i);
                j++;  
            } else {
                i++;  
            }
        }

        return maxDist;
    }
}
